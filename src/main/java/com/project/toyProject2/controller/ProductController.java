package com.project.toyProject2.controller;

import com.project.toyProject2.domain.dto.product.ProductListDTO;
import com.project.toyProject2.domain.dto.product.ProductListRequestDTO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.domain.vo.ProductVO;
import com.project.toyProject2.service.ImageService;
import com.project.toyProject2.service.MemberService;
import com.project.toyProject2.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product/*")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ImageService imageService;
    private final ProductService productService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model, HttpSession session,
                       ProductListRequestDTO productListRequestDTO) {

        if (session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
            SecurityContext context = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
            Optional<MemberVO> loginMember = memberService.findMember(context.getAuthentication().getName());
            model.addAttribute("loginMember", loginMember);
        }

        List<ProductListDTO> productList = productService.findAllProduct(productListRequestDTO);

        model.addAttribute("productList", productList);
        model.addAttribute("productListRequestDTO", productListRequestDTO);
        return "product/productList";
    }
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("product", new ProductVO());
        return "product/productForm";
    }
    @PostMapping("/form")
    public String form(@ModelAttribute ProductVO productVO,@RequestParam("imageFile") MultipartFile[] files) {
        productService.saveProduct(productVO,files);
        return "redirect:/product/list";
    }
    @GetMapping("/detail/{productId}")
    public String detail(@PathVariable("productId")Long productId, Model model,HttpSession session) {
        if (session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
            SecurityContext context = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
            Optional<MemberVO> loginMember = memberService.findMember(context.getAuthentication().getName());
            model.addAttribute("loginMember", loginMember);
        }
        model.addAttribute("imagePaths",imageService.findImagePaths(productId));
        productService.updateReadCount(productId);
        model.addAttribute("product", productService.findProduct(productId));
        return "product/productDetail";
    }
    @PostMapping("/delete/{productId}")
    public String delete(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list";
    }



}
