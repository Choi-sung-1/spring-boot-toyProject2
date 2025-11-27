package com.project.toyProject2.controller;

import com.project.toyProject2.domain.dto.cart.CartAddDTO;
import com.project.toyProject2.domain.vo.CartVO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.service.CartService;
import com.project.toyProject2.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/cart/*")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String cart(Model model, HttpSession session) {
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Optional<MemberVO> loginMember = memberService.findMember(context.getAuthentication().getName());
            model.addAttribute("loginMember", loginMember);
            model.addAttribute("cartList", cartService.findAllCartByMemberId(loginMember.get().getMemberId()));
        }
        return "/cart/cartList";
    }


    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addCart(@RequestBody CartAddDTO cartDTO) {
        CartVO findCartItem = cartService.findCartItem(cartDTO.getMemberId(), cartDTO.getProductId());
        if (findCartItem != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 장바구니에 담겨있습니다.");
        }
        cartService.addCart(cartDTO);
        return ResponseEntity.ok("장바구니에 상품이 추가되었습니다.");
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long cartId) {
        cartService.deleteCartItem(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll/{memberId}")
    public ResponseEntity<?> deleteAllCartItem(@PathVariable Long memberId) {
        cartService.deleteAllCartItemByMemberId(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/updateQuantity/{cartId}")
    @ResponseBody
    public ResponseEntity<String> updateQuantity(@PathVariable Long cartId, @RequestBody CartVO cartVO) {
        cartService.updateCartItemQuantity(cartId, cartVO.getCartQuantity());
        return ResponseEntity.ok("success");
    }

}