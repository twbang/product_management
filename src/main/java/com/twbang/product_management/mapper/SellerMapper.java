package com.twbang.product_management.mapper;

import java.util.List;

import com.twbang.product_management.data.SellerVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SellerMapper {
    public List<SellerVO> getSellerInfo(Integer offset);
    public Integer getSellerCount();
    public void addSeller(SellerVO data);
    public void deleteSeller(Integer seq);
    public SellerVO getSellerInfoBySeq(Integer seq);

    public void updateSeller(SellerVO data);
}
