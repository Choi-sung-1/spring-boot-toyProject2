package com.project.toyProject2.controller;

import com.project.toyProject2.domain.dto.wishList.WishListPageDTO;
import com.project.toyProject2.domain.dto.wishList.WishListRequestDTO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.domain.vo.WishListVO;
import com.project.toyProject2.service.MemberService;
import com.project.toyProject2.service.WishListService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/wishList/*")
@RequiredArgsConstructor
@Slf4j
public class WishListController {
    private final WishListService wishListService;
    private final MemberService memberService;
    @GetMapping("/list")
    public String wishList(HttpSession session, Model model) {
        if (session.getAttribute("SPRING_SECURITY_CONTEXT")!=null) {
            SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Optional<MemberVO> loginMember = memberService.findMember(context.getAuthentication().getName());
            if (loginMember.isPresent()) {
                List<WishListPageDTO> wishList = wishListService.getWishListByMemberId(loginMember.get().getMemberId());
                model.addAttribute("loginMember",loginMember);
                model.addAttribute("wishList", wishList);
            }
        }
        return "/wishList/list";
    }
//    productList에서 찜하기를 눌렀을경우
    @PostMapping("/addFetch")
    @ResponseBody
    public Boolean addFetch(@RequestBody WishListRequestDTO wishListRequestDTO) {
        WishListVO findWishProduct = wishListService.findWishProductById(wishListRequestDTO.getMemberId(), wishListRequestDTO.getProductId());
        if (findWishProduct != null) {
            wishListService.deleteWishList(wishListRequestDTO.getMemberId(), wishListRequestDTO.getProductId());
            return false;
        }
        WishListVO newWish = new WishListVO();
        newWish.setMemberId(wishListRequestDTO.getMemberId());
        newWish.setProductId(wishListRequestDTO.getProductId());

        wishListService.saveWishList(newWish);
        return true;
    }
}
