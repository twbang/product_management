package com.twbang.product_management.api;

import java.util.Map;

import com.twbang.product_management.data.SellerVO;
import com.twbang.product_management.service.SellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerAPIController {
    @Autowired
    SellerService service;

    @PostMapping("/seller/add")
    public Map<String, Object> postSellerAdd(@RequestBody SellerVO data) throws Exception {
        return service.addSeller(data);
    }

    @DeleteMapping("/seller/delete")
    public Map<String, Object> deleteSeller(@RequestParam Integer seq) {
        return service.deleteSeller(seq);
    }

    @GetMapping("/seller/get")
    public Map<String, Object> getSellerInfoBySeq(@RequestParam Integer seq) {
        return service.getSellerInfoBySeq(seq);
    }

    @PatchMapping("/seller/update")
    public Map<String, Object> patchSellerInfo(@RequestBody SellerVO data) {
        return service.updateSellerInfo(data);
    }
}
