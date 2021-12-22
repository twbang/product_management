package com.twbang.product_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.twbang.product_management.data.ProductCategoryVO;
import com.twbang.product_management.data.ProductHistoryVO;
import com.twbang.product_management.data.ProductVO;
import com.twbang.product_management.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductMapper mapper;

    public Map<String, Object> getProductList(Integer offset, String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (offset == null) {
            offset = 0;
            resultMap.put("offset", offset);
        }
        if (keyword == null) {
            keyword = "%%";
            resultMap.put("keyword", "");
        } else {
            resultMap.put("keyword", keyword);
            keyword = "%" + keyword + "%";
        }

        List<ProductVO> list = mapper.getProductInfo(offset, keyword);

        Integer cnt = mapper.getProductCount(keyword);
        Integer page_cnt = cnt / 10 + (cnt % 10 > 0 ? 1 : 0);

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> addProduct(ProductVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (data.getPi_name() == null || data.getPi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "제품명을 입력하세요.");
            return resultMap;
        }
        if (data.getPi_sub() == null || data.getPi_sub().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "제품명을 입력하세요.");
            return resultMap;
        }
        if (data.getPi_price() == null || data.getPi_price() == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "제품명을 입력하세요.");
            return resultMap;
        }

        mapper.addProduct(data);
        resultMap.put("status", true);
        resultMap.put("message", "제품이 추가되었습니다.");

        Integer seq = mapper.selectLatestDataSeq();
        ProductHistoryVO history = new ProductHistoryVO();
        history.setPh_pi_seq(seq);
        history.setPh_type("new");
        String content = data.getPi_pci_seq()+"|"+data.getPi_name() + "|" + data.getPi_sub() + "|" + data.getPi_price() + "|" + data.getPi_sell()
                + "|" + data.getPi_as();
        history.setPh_content(content);
        mapper.insertProductHistory(history);

        return resultMap;
    }

    public Map<String, Object> getCategoryByKeyword(String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (keyword == null)
            keyword = "%%";
        keyword = "%" + keyword + "%";
        List<ProductCategoryVO> list = mapper.getCategoryByKeyword(keyword);
        resultMap.put("status", true);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> deleteProduct(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteProduct(seq);
        resultMap.put("status", true);
        resultMap.put("message", "제품이 삭제되었습니다.");

        ProductHistoryVO history = new ProductHistoryVO();
        history.setPh_pi_seq(seq);
        history.setPh_type("delete");
        mapper.insertProductHistory(history);
        return resultMap;
    }

    public Map<String, Object> getProductInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getProductInfoBySeq(seq));
        return resultMap;
    }

    public Map<String, Object> updateProductInfo(ProductVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        mapper.updateProduct(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        ProductHistoryVO history = new ProductHistoryVO();
        history.setPh_pi_seq(data.getPi_seq());
        history.setPh_type("update");
        String content = data.getPi_name() + "|" + data.getPi_sub() + "|" + data.getPi_price() + "|" + data.getPi_sell()
                + "|" + data.getPi_as();
        history.setPh_content(content);
        mapper.insertProductHistory(history);
        return resultMap;
    }
}
