package com.twbang.product_management.controller;

import java.util.Map;

import com.twbang.product_management.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Autowired ProductService service;

    @GetMapping("/product")
    public String getProduct(Model model, @RequestParam @Nullable Integer offset, @RequestParam @Nullable String keyword) {
        Map<String, Object> resultMap = service.getProductList(offset, keyword);
        model.addAttribute("data", resultMap);
        return "/product/list";
    }
}
