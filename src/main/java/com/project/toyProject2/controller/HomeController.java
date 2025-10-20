package com.project.toyProject2.controller;

import com.project.toyProject2.security.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String home(@AuthenticationPrincipal CustomUserDetails userDetails, HttpSession session, Model model) {
        if (userDetails != null) {
            model.addAttribute("loginUser",userDetails);
            System.out.println("로그인 아이디: " + userDetails.getUsername());
            // 세션에서 SecurityContext 확인
            SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Long memberId = userDetails.getMember().getMemberId();
            session.setAttribute("sessionId", memberId);
            System.out.println("로그인 사용자 : "+context.getAuthentication().getName());
            System.out.println("세션 아이디 :"+session.getAttribute("sessionId"));
        }else {
            System.out.println("로그인 안됨");
        }
        return "home";
    }
}
