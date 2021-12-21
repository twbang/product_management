package com.twbang.product_management.api;

import java.util.Map;

import com.twbang.product_management.data.ProductManagerVO;
import com.twbang.product_management.service.ProductManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductManagerAPIController {
    @Autowired ProductManagerService service;
    @PostMapping("/productmanager/add")
    public Map<String, Object> postProductManagerAdd(@RequestBody ProductManagerVO data) throws Exception {
        System.out.println(data);
        return service.addProductManagerInfo(data);
    }
    @GetMapping("/productcategory/keyword")
    public Map<String, Object> getCategoryByKeyword(@RequestParam @Nullable String keyword) {
        return service.getCategoryByKeyword(keyword);
    }
    @DeleteMapping("/productmanager/delete")
    public Map<String, Object> deleteProductManager(@RequestParam Integer seq) {
        return service.deleteProductManager(seq);
    }
    @GetMapping("/productmanager/get")
    public Map<String, Object> getProductManagerInfoBySeq(@RequestParam Integer seq) {
        return service.getProductManagerInfoBySeq(seq);
    }
    @PatchMapping("/productmanager/update")
    public Map<String, Object> patchProductManagerInfo(@RequestBody ProductManagerVO data) {
        return service.updateProductManagerInfo(data);
    }
}
