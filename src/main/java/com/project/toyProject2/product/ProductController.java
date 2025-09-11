package com.project.toyProject2.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/*")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/list")
    public String list(Model model) {
        return "product/productList";
    }
    @GetMapping("/form")
    public String form(Model model) {
        return "product/productForm";
    }
    @GetMapping("/detail/{productId}")
    public String detail(@PathVariable("productId")Long productId, Model model) {
        return "product/productDetail";
    }
}
