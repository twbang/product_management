package com.twbang.product_management.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {
    public Integer getTotalProductCnt();

    public Integer getSellProductCnt();

    public Integer getNonpurchaseProductCnt();

    public Integer getFixProductCnt();

    public Integer getTotalSellerCnt();

    public Integer getWaitSellerCnt();

    public Integer getSuspendedSellerCnt();

    public Integer getTotalBuyerCnt();

    public Integer getWaitBuyerCnt();

    public Integer getSuspendedBuyerCnt();

    public Integer getTotalPMCnt();

    public Integer getWorkPMCnt();

    public Integer getDayoffPMCnt();

    public Integer getTotalPGMCnt();

    public Integer getWorkPGMCnt();

    public Integer getDayoffPGMCnt();

    public Date getProductUpdateDate();

    public Date getSellerUpdateDate();

    public Date getBuyerUpdateDate();

    public Date getProductManagerUpdateDate();
}
