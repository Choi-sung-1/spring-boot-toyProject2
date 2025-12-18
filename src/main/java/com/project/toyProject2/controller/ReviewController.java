package com.project.toyProject2.controller;

import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.domain.vo.ReviewVO;
import com.project.toyProject2.service.image.ImageService;
import com.project.toyProject2.service.member.MemberService;
import com.project.toyProject2.service.product.ProductService;
import com.project.toyProject2.service.review.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review/*")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ImageService imageService;
    private final ProductService productService;
    private final MemberService memberService;

    @GetMapping("/write/{productId}")
    public String writeReview(@PathVariable Long productId, Model model) {
            List<String> imagePaths =imageService.findImagePaths("PRODUCT", productId);
            model.addAttribute("product",productService.findProductByPk(productId));
            model.addAttribute("imagePaths", imagePaths.get(0));
            model.addAttribute("reviewVO",new ReviewVO());
        return "review/reviewWrite";
    }
    @PostMapping("/write/{productId}")
    public String writeReview(@ModelAttribute ReviewVO reviewVO,HttpSession session,
                              @PathVariable Long productId) {
        if (session.getAttribute("SPRING_SECURITY_CONTEXT")!=null) {
            SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Optional<MemberVO> loginMember = memberService.findMember(context.getAuthentication().getName());
            reviewVO.setMemberId(loginMember.get().getMemberId());
            reviewVO.setProductId(productId);
            reviewService.writeReview(reviewVO);
        }
        return "redirect:/product/detail/{productId}";
    }
    @GetMapping("/list/{productId}")
    public String list(Model model,@PathVariable Long productId) {
        List<String> imagePaths =imageService.findImagePaths("PRODUCT", productId);

        model.addAttribute("productImage",imagePaths.get(0));
        model.addAttribute("product",productService.findProductByPk(productId));
        model.addAttribute("reviewList",reviewService.reviewListPage(productId));
        return "review/reviewList";
    }
}
