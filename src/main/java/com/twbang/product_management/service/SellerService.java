package com.twbang.product_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.twbang.product_management.data.SellerHistoryVO;
import com.twbang.product_management.data.SellerVO;
import com.twbang.product_management.mapper.SellerMapper;
import com.twbang.product_management.utils.AESAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerMapper mapper;

    public Map<String, Object> getSellerList(Integer offset, String keyword) {

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

        List<SellerVO> list = mapper.getSellerInfo(offset, keyword);

        Integer cnt = mapper.getSellerCount(keyword);
        Integer page_cnt = cnt / 10 + (cnt % 10 > 0 ? 1 : 0);

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> addSeller(SellerVO data) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (data.getSi_id() == null || data.getSi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디를 입력하세요.");
            return resultMap;
        }
        if (data.getSi_pwd() == null || data.getSi_pwd().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "비밀번호를 입력하세요.");
            return resultMap;
        }
        if (data.getSi_email() == null || data.getSi_email().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "이메일을 입력하세요.");
            return resultMap;
        }
        if (data.getSi_name() == null || data.getSi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "이름을 입력하세요.");
            return resultMap;
        }
        if (data.getSi_address() == null || data.getSi_address().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "주소를 입력하세요.");
            return resultMap;
        }
        if (data.getSi_phone_number() == null || data.getSi_phone_number().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "전화번호를 입력하세요.");
            return resultMap;
        }
        if (data.getSi_birth() == null || data.getSi_birth().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "생년월일을 입력하세요.");
            return resultMap;
        }

        String pwd = data.getSi_pwd();
        String encrypted = AESAlgorithm.Encrypt(pwd);
        data.setSi_pwd(encrypted);

        mapper.addSeller(data);
        resultMap.put("status", true);
        resultMap.put("message", "판매자가 추가되었습니다.");

        Integer seq = mapper.selectLatestDataSeq();
        SellerHistoryVO history = new SellerHistoryVO();
        history.setSh_si_seq(seq);
        history.setSh_type("new");
        String content = data.getSi_id() + "|" + data.getSi_pwd() + "|" + data.getSi_birth() + "|" + data.getSi_name()
                + "|" + data.getSi_address() + "|" + data.getSi_phone_number() + "|" + data.getSi_status();
        history.setSh_content(content);
        mapper.insertSellerHistory(history);
        return resultMap;
    }

    public Map<String, Object> deleteSeller(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteSeller(seq);
        resultMap.put("status", true);
        resultMap.put("message", "판매자가 삭제되었습니다.");

        SellerHistoryVO history = new SellerHistoryVO();
        history.setSh_si_seq(seq);
        history.setSh_type("delete");
        mapper.insertSellerHistory(history);
        return resultMap;
    }

    public Map<String, Object> getSellerInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getSellerInfoBySeq(seq));
        return resultMap;
    }

    public Map<String, Object> updateSellerInfo(SellerVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        mapper.updateSeller(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        SellerHistoryVO history = new SellerHistoryVO();
        history.setSh_si_seq(data.getSi_seq());
        history.setSh_type("update");
        String content = data.getSi_id() + "|" + data.getSi_birth() + "|" + data.getSi_name()
                + "|" + data.getSi_address() + "|" + data.getSi_phone_number() + "|" + data.getSi_status();
        history.setSh_content(content);
        mapper.insertSellerHistory(history);
        return resultMap;
    }
}
