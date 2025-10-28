package com.project.toyProject2.controller;

import com.project.toyProject2.domain.dto.CartAddDTO;
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
@RequestMapping("/order/*")
@RequiredArgsConstructor
public class OrderController {
    @GetMapping("/list")
    public String list(){
        return "order/orderList";
    }

}
