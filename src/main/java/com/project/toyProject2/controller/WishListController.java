package com.project.toyProject2.controller;

import com.project.toyProject2.domain.dto.wishList.WishListDTO;
import com.project.toyProject2.domain.vo.WishListVO;
import com.project.toyProject2.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishList/*")
@RequiredArgsConstructor
@Slf4j
public class WishListController {
    private final WishListService wishListService;

    @GetMapping("/list")
    public String wishList() {
        return "/wishList/list";
    }
//    productList에서 찜하기를 눌렀을경우
    @PostMapping("/addFetch")
    @ResponseBody
    public Boolean addFetch(@RequestBody WishListDTO wishListDTO) {
        WishListVO findWishProduct = wishListService.findWishProductById(wishListDTO.getMemberId(), wishListDTO.getProductId());
        if (findWishProduct != null) {
            wishListService.deleteWishList(wishListDTO.getMemberId(), wishListDTO.getProductId());
            return false;
        }
        WishListVO newWish = new WishListVO();
        newWish.setMemberId(wishListDTO.getMemberId());
        newWish.setProductId(wishListDTO.getProductId());

        wishListService.saveWishList(newWish);
        return true;
    }

//    postDetail에서 찜하기를 눌렀을경우
    @PostMapping("/add")
    public String addWishList() {
        return "redirect:/wishList/";
    }
}
