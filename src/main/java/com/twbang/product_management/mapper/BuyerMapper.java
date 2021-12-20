package com.twbang.product_management.mapper;

import java.util.List;

import com.twbang.product_management.data.BuyerHistoryVO;
import com.twbang.product_management.data.BuyerVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BuyerMapper {
    public List<BuyerVO> getBuyerInfo(Integer offset, String keyword);

    public Integer getBuyerCount(String keyword);

    public void addBuyer(BuyerVO data);

    public void deleteBuyer(Integer seq);

    public BuyerVO getBuyerInfoBySeq(Integer seq);

    public void updateBuyer(BuyerVO data);

    public Integer selectLatestDataSeq();

    public void insertBuyerHistory(BuyerHistoryVO data);
}
