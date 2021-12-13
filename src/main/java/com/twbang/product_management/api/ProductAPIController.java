package com.twbang.product_management.api;

import java.util.Map;

import com.twbang.product_management.data.ProductVO;
import com.twbang.product_management.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductAPIController {
    @Autowired ProductService service;

    @PostMapping("/product/add")
    public Map<String, Object> postProductAdd(@RequestBody ProductVO data) {
        return service.addProduct(data);
    }
    @DeleteMapping("/product/delete")
    public Map<String, Object> deleteProduct(@RequestParam Integer seq) {
        return service.deleteProduct(seq);
    }
}
