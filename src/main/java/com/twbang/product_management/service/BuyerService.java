package com.twbang.product_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.twbang.product_management.data.BuyerHistoryVO;
import com.twbang.product_management.data.BuyerVO;
import com.twbang.product_management.mapper.BuyerMapper;
import com.twbang.product_management.utils.AESAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {
    @Autowired
    BuyerMapper mapper;

    public Map<String, Object> getBuyerList(Integer offset, String keyword) {

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

        List<BuyerVO> list = mapper.getBuyerInfo(offset, keyword);

        Integer cnt = mapper.getBuyerCount(keyword);
        Integer page_cnt = cnt / 10 + (cnt % 10 > 0 ? 1 : 0);

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> addBuyer(BuyerVO data) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (data.getBi_id() == null || data.getBi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디를 입력하세요.");
            return resultMap;
        }
        if (data.getBi_pwd() == null || data.getBi_pwd().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "비밀번호를 입력하세요.");
            return resultMap;
        }
        if (data.getBi_email() == null || data.getBi_email().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "이메일을 입력하세요.");
            return resultMap;
        }
        if (data.getBi_name() == null || data.getBi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "이름을 입력하세요.");
            return resultMap;
        }
        if (data.getBi_address() == null || data.getBi_address().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "주소를 입력하세요.");
            return resultMap;
        }
        if (data.getBi_phone_number() == null || data.getBi_phone_number().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "전화번호를 입력하세요.");
            return resultMap;
        }
        if (data.getBi_birth() == null || data.getBi_birth().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "생년월일을 입력하세요.");
            return resultMap;
        }

        String pwd = data.getBi_pwd();
        String encrypted = AESAlgorithm.Encrypt(pwd);
        data.setBi_pwd(encrypted);

        mapper.addBuyer(data);
        resultMap.put("status", true);
        resultMap.put("message", "판매자가 추가되었습니다.");

        Integer seq = mapper.selectLatestDataSeq();
        BuyerHistoryVO history = new BuyerHistoryVO();
        history.setBh_bi_seq(seq);
        history.setBh_type("new");
        String content = data.getBi_id() + "|" + data.getBi_pwd() + "|" + data.getBi_birth() + "|" + data.getBi_name()
                + "|" + data.getBi_address() + "|" + data.getBi_phone_number() + "|" + data.getBi_status();
        history.setBh_content(content);
        mapper.insertBuyerHistory(history);
        return resultMap;
    }

    public Map<String, Object> deleteBuyer(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteBuyer(seq);
        resultMap.put("status", true);
        resultMap.put("message", "판매자가 삭제되었습니다.");

        BuyerHistoryVO history = new BuyerHistoryVO();
        history.setBh_bi_seq(seq);
        history.setBh_type("delete");
        mapper.insertBuyerHistory(history);
        return resultMap;
    }

    public Map<String, Object> getBuyerInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getBuyerInfoBySeq(seq));
        return resultMap;
    }

    public Map<String, Object> updateBuyerInfo(BuyerVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        mapper.updateBuyer(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        BuyerHistoryVO history = new BuyerHistoryVO();
        history.setBh_bi_seq(data.getBi_seq());
        history.setBh_type("update");
        String content = data.getBi_id() + "|" + data.getBi_birth() + "|" + data.getBi_name()
                + "|" + data.getBi_address() + "|" + data.getBi_phone_number() + "|" + data.getBi_status();
        history.setBh_content(content);
        mapper.insertBuyerHistory(history);
        return resultMap;
    }
}
