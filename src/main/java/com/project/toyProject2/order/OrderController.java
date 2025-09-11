package com.project.toyProject2.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/*")
public class OrderController {

    @GetMapping("/list")
    public String list(){
        return "order/orderList";
    }
    @GetMapping("/cart")
    public String cart(){
        return "order/cart";
    }
}
