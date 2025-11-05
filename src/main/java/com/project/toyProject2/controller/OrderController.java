package com.project.toyProject2.controller;

import com.project.toyProject2.domain.dto.CartListDTO;
import com.project.toyProject2.domain.dto.OrderPaymentDTO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.service.CartService;
import com.project.toyProject2.service.MemberService;
import com.project.toyProject2.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/order/*")
@RequiredArgsConstructor
public class OrderController {
    private final MemberService memberService;
    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping("/list")
    public String list(){
        return "order/orderList";
    }

    @GetMapping("/orderPayment")
    public String payment(Model model,HttpSession session){
        if(session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
            SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Optional<MemberVO> loginMember = memberService.findMember(context.getAuthentication().getName());
            if(loginMember.isPresent()){
                OrderPaymentDTO orderPaymentDTO =orderService.prepareCartPayment(loginMember.get());
                model.addAttribute("orderPaymentDTO",orderPaymentDTO);
                log.info(orderPaymentDTO.toString());
            }
        }
        return "order/orderPayment";
    }
}
