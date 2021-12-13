package com.twbang.product_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.twbang.product_management.data.SellerVO;
import com.twbang.product_management.mapper.SellerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired SellerMapper mapper;

    public Map<String, Object> getSellerList(Integer offset) {
        if(offset == null) offset=0;

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<SellerVO> list = mapper.getSellerInfo(offset);

        Integer cnt = mapper.getSellerCount();
        Integer page_cnt = cnt/10+(cnt%10>0?1:0);

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }
}
