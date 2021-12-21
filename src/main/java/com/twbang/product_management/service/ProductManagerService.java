package com.twbang.product_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.twbang.product_management.data.ProductCategoryVO;
import com.twbang.product_management.data.ProductManagerVO;
import com.twbang.product_management.mapper.ProductManagerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManagerService {
    @Autowired ProductManagerMapper mapper;

    public Map<String, Object> addProductManagerInfo(ProductManagerVO data) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(data.getPmi_pci_seq()==0 || data.getPmi_pci_seq() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "id");
            resultMap.put("message", "id를 입력해주세요");
            return resultMap;
        }

        mapper.addProductManagerInfo(data);
        resultMap.put("status", true);
        resultMap.put("message", "제품관리자가 추가되었습니다.");
        return resultMap;
    }

    public Map<String, Object> getProductManagerList(String type, String keyword, Integer offset) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(keyword == null) {
            resultMap.put("keyword", keyword);
            keyword= "%%";
        }
        else {
            resultMap.put("keyword", keyword);
            keyword = "%"+keyword+"%";
        }

        resultMap.put("type", type);

        if(offset == null) offset=0;
        
        List<ProductManagerVO> list = mapper.getProductManagerList(type, keyword, offset);
        Integer cnt = mapper.getProductManagerCnt(type, keyword);

        Integer page = cnt/10;
        if(cnt%10>0) page++;

        resultMap.put("status", true);
        resultMap.put("pageCnt", page);
        resultMap.put("list", list);

        return resultMap;
    }

    public Map<String, Object> getCategoryByKeyword(String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(keyword == null) keyword = "%%";
        keyword = "%"+keyword+"%";
        List<ProductCategoryVO> list = mapper.getCategoryByKeyword(keyword);
        resultMap.put("status", true);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> deleteProductManager(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteProductManager(seq);
        resultMap.put("status", true);
        resultMap.put("message", "제품관리자가 삭제되었습니다.");
        return resultMap;
    }

    public Map<String, Object> getProductManagerInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getProductManagerInfoBySeq(seq));
        return resultMap;
    }

    public Map<String, Object> updateProductManagerInfo(ProductManagerVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.updateProductManager(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");
        return resultMap;
    }
}
