package com.project.toyProject2.controller;

import com.project.toyProject2.domain.member.MemberVO;
import com.project.toyProject2.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberVO", new MemberVO());
        return "member/join";
    }
    @PostMapping("/join")
    public String join(MemberVO memberVO) {
        memberService.join(memberVO);
        return "redirect:/member/list";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("memberVO", new MemberVO());
        return "member/login";
    }
}
