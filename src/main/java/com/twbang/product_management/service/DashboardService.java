package com.twbang.product_management.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.twbang.product_management.mapper.DashboardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    DashboardMapper mapper;

    public Map<String, Object> getCounts() {
        List<Integer> productCntList = new ArrayList<Integer>();
        productCntList.add(mapper.getTotalProductCnt());
        productCntList.add(mapper.getSellProductCnt());
        productCntList.add(mapper.getFixProductCnt());
        productCntList.add(mapper.getNonpurchaseProductCnt());

        List<Integer> sellerCntList = new ArrayList<Integer>();
        sellerCntList.add(mapper.getTotalSellerCnt());
        sellerCntList.add(mapper.getWaitSellerCnt());
        sellerCntList.add(mapper.getSuspendedSellerCnt());

        List<Integer> buyerCntList = new ArrayList<Integer>();
        buyerCntList.add(mapper.getTotalBuyerCnt());
        buyerCntList.add(mapper.getWaitBuyerCnt());
        buyerCntList.add(mapper.getSuspendedBuyerCnt());

        List<Integer> pmCntList = new ArrayList<Integer>();
        pmCntList.add(mapper.getTotalPMCnt());
        pmCntList.add(mapper.getWorkPMCnt());
        pmCntList.add(mapper.getDayoffPMCnt());

        List<Integer> pgmCntList = new ArrayList<Integer>();
        pgmCntList.add(mapper.getTotalPGMCnt());
        pgmCntList.add(mapper.getWorkPGMCnt());
        pgmCntList.add(mapper.getDayoffPGMCnt());

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("product", productCntList);
        map.put("seller", sellerCntList);
        map.put("buyer", buyerCntList);
        map.put("productmanager", pmCntList);
        map.put("pagemanager", pgmCntList);
        return map;
    }

    public Map<String, Object> getUpdateDate() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("product", mapper.getProductUpdateDate());
        resultMap.put("seller", mapper.getSellerUpdateDate());
        resultMap.put("buyer", mapper.getBuyerUpdateDate());
        resultMap.put("product_manager", mapper.getProductManagerUpdateDate());

        return resultMap;
    }
}
