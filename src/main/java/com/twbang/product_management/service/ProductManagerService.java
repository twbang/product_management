package com.twbang.product_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.twbang.product_management.data.ProductCategoryVO;
import com.twbang.product_management.data.ProductManagerHistoryVO;
import com.twbang.product_management.data.ProductManagerVO;
import com.twbang.product_management.mapper.ProductManagerMapper;
import com.twbang.product_management.utils.AESAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManagerService {
    @Autowired
    ProductManagerMapper mapper;

    public Map<String, Object> addProductManagerInfo(ProductManagerVO data) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (data.getPmi_pci_seq() == 0 || data.getPmi_pci_seq() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "category");
            resultMap.put("message", "카테고리를 입력해주세요");
            return resultMap;
        }
        if (data.getPmi_id() == "" || data.getPmi_id() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "id");
            resultMap.put("message", "id를 입력해주세요");
            return resultMap;
        }
        if (data.getPmi_pwd() == "" || data.getPmi_pwd() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "pwd");
            resultMap.put("message", "pwd를 입력해주세요");
            return resultMap;
        }
        if (data.getPmi_name() == "" || data.getPmi_name() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "name");
            resultMap.put("message", "이름을 입력해주세요");
            return resultMap;
        }
        if (data.getPmi_birth() == "" || data.getPmi_birth() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "birth");
            resultMap.put("message", "생년월일을 입력해주세요");
            return resultMap;
        }
        if (data.getPmi_phone_number() == "" || data.getPmi_phone_number() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "phone_number");
            resultMap.put("message", "전화번호를 입력해주세요");
            return resultMap;
        }
        if (data.getPmi_email() == "" || data.getPmi_email() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "email");
            resultMap.put("message", "이메일을 입력해주세요");
            return resultMap;
        }

        String pwd = data.getPmi_pwd();
        String encrypted = AESAlgorithm.Encrypt(pwd);
        data.setPmi_pwd(encrypted);

        mapper.addProductManagerInfo(data);

        Integer seq = mapper.selectLatestDataSeq();
        ProductManagerHistoryVO history = new ProductManagerHistoryVO();
        history.setPmh_pmi_seq(seq);
        history.setPmh_type("new");
        String content = data.getPmi_pci_seq() + "|" + data.getPmi_id() + "|" + data.getPmi_pwd() + "|"
                + data.getPmi_name() + "|" + data.getPmi_birth() + "|" + data.getPmi_phone_number() + "|"
                + data.getPmi_email() + "|" + data.getPmi_status();
        history.setPmh_content(content);
        mapper.insertProductManagerHistory(history);

        resultMap.put("status", true);
        resultMap.put("message", "제품관리자가 추가되었습니다.");
        return resultMap;
    }

    public Map<String, Object> getProductManagerList(String type, String keyword, Integer offset) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (keyword == null) {
            resultMap.put("keyword", keyword);
            keyword = "%%";
        } else {
            resultMap.put("keyword", keyword);
            keyword = "%" + keyword + "%";
        }

        resultMap.put("type", type);

        if (offset == null)
            offset = 0;

        List<ProductManagerVO> list = mapper.getProductManagerList(type, keyword, offset);
        Integer cnt = mapper.getProductManagerCnt(type, keyword);

        Integer page = cnt / 10;
        if (cnt % 10 > 0)
            page++;

        resultMap.put("status", true);
        resultMap.put("pageCnt", page);
        resultMap.put("list", list);

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

    public Map<String, Object> deleteProductManager(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteProductManager(seq);
        resultMap.put("status", true);
        resultMap.put("message", "제품관리자가 삭제되었습니다.");

        ProductManagerHistoryVO history = new ProductManagerHistoryVO();
        history.setPmh_pmi_seq(seq);
        history.setPmh_type("delete");
        mapper.insertProductManagerHistory(history);
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

        ProductManagerHistoryVO history = new ProductManagerHistoryVO();
        history.setPmh_pmi_seq(data.getPmi_seq());
        history.setPmh_type("update");
        String content = data.getPmi_pci_seq() + "|" + data.getPmi_id() + "|"
                + data.getPmi_name() + "|" + data.getPmi_birth() + "|" + data.getPmi_phone_number() + "|"
                + data.getPmi_email() + "|" + data.getPmi_status();
        history.setPmh_content(content);
        mapper.insertProductManagerHistory(history);
        return resultMap;
    }
}
