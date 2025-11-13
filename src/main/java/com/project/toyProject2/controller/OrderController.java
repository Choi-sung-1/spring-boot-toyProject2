package com.project.toyProject2.controller;

import com.project.toyProject2.domain.dto.OrderPaymentDTO;
import com.project.toyProject2.domain.dto.OrderRequestDTO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.service.MemberService;
import com.project.toyProject2.service.MemberServiceImpl;
import com.project.toyProject2.service.OrderServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/order/*")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final MemberServiceImpl memberService;
    private final OrderServiceImpl orderService;
    @GetMapping("/orderPayment")
    public String orderPayment(Model model, HttpSession session) {
        if (session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
            SecurityContext context = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
            Optional<MemberVO> loginMember = memberService.findMember(context.getAuthentication().getName());
            OrderPaymentDTO orderPaymentDTO =orderService.orderPaymentPage(loginMember.get());
            model.addAttribute("orderPaymentDTO", orderPaymentDTO);
        }
        return "/order/orderPayment";
    }
    @PostMapping("/orderPayment")
    public String orderPayment(@ModelAttribute OrderRequestDTO orderRequestDTO){
//       order service 추가
        orderService.saveOrder(orderRequestDTO);
        return "redirect:/order/orderList";
    }
}
