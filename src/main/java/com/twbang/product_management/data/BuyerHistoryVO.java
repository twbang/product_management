package com.twbang.product_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class BuyerHistoryVO {
    private Integer bh_seq;
    private String bh_type;
    private String bh_content;
    private Date bh_reg_dt;
    private Integer bh_bi_seq;
}
