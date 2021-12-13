package com.twbang.product_management.controller;

import java.util.Map;

import com.twbang.product_management.service.SellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SellerController {
    @Autowired SellerService service;
    @GetMapping("/seller")
    public String getSeller(Model model, @RequestParam @Nullable Integer offset) {
        Map<String, Object> resultMap = service.getSellerList(offset);
        model.addAttribute("data", resultMap);
        return "/seller/list";
    }
}
