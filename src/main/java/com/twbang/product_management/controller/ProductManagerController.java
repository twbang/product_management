package com.twbang.product_management.controller;

import java.util.Map;

import com.twbang.product_management.service.ProductManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductManagerController {
    @Autowired ProductManagerService service;
    @GetMapping("/product_manager")
    public String getProductManager(Model model, @RequestParam @Nullable String type, @RequestParam @Nullable String keyword, @RequestParam @Nullable Integer offset) throws Exception{
        Map<String, Object> resultMap = service.getProductManagerList(type, keyword, offset);
        model.addAttribute("data", resultMap);
        return "/product_manager/list";
    }
}
