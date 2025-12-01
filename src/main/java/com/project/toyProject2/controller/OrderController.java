package com.project.toyProject2.controller;

import com.project.toyProject2.domain.dto.order.OrderItemDetailDTO;
import com.project.toyProject2.domain.dto.order.OrderPaymentDTO;
import com.project.toyProject2.domain.dto.order.OrderRequestDTO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.domain.vo.OrderVO;
import com.project.toyProject2.repository.ProductDAO;
import com.project.toyProject2.service.MemberServiceImpl;
import com.project.toyProject2.service.OrderItemServiceImpl;
import com.project.toyProject2.service.OrderServiceImpl;
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
@RequestMapping("/order/*")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final MemberServiceImpl memberService;
    private final OrderServiceImpl orderService;
    private final OrderItemServiceImpl orderItemService;
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
        orderService.saveOrder(orderRequestDTO);
        return "redirect:/order/orderList";
    }
    @GetMapping("/orderList")
    public String orderList(Model model, HttpSession session) {
        if (session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
            SecurityContext context = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
            Optional<MemberVO> loginMember = memberService.findMember(context.getAuthentication().getName());
            List<OrderVO> orderList = orderService.orderList(loginMember.get().getMemberId());
            model.addAttribute("orderList", orderList);
        }
        return "/order/orderList";
    }
    @GetMapping("/orderDetail/{orderId}")
    public String orderDetail(Model model, HttpSession session, @PathVariable Long orderId) {
        OrderVO order = orderService.findOrder(orderId);
        MemberVO member = memberService.findMemberByPk(order.getMemberId());
        List<OrderItemDetailDTO> orderItems = orderItemService.findOrderItemById(orderId);
        log.info(orderItems.toString());
        model.addAttribute("order", order);
        model.addAttribute("member", member);
        model.addAttribute("orderItems", orderItems);
        return "/order/orderDetail";
    }
}
