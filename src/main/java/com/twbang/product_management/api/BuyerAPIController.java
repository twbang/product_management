package com.twbang.product_management.api;

import java.util.Map;

import com.twbang.product_management.data.BuyerVO;
import com.twbang.product_management.service.BuyerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyerAPIController {
    @Autowired
    BuyerService service;

    @PostMapping("/buyer/add")
    public Map<String, Object> postBuyerAdd(@RequestBody BuyerVO data) {
        return service.addBuyer(data);
    }

    @DeleteMapping("/buyer/delete")
    public Map<String, Object> deleteBuyer(@RequestParam Integer seq) {
        return service.deleteBuyer(seq);
    }

    @GetMapping("/buyer/get")
    public Map<String, Object> getBuyerInfoBySeq(@RequestParam Integer seq) {
        return service.getBuyerInfoBySeq(seq);
    }

    @PatchMapping("/buyer/update")
    public Map<String, Object> patchBuyerInfo(@RequestBody BuyerVO data) {
        return service.updateBuyerInfo(data);
    }
}
