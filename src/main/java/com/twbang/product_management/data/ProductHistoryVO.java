package com.twbang.product_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class ProductHistoryVO {
    private Integer ph_seq;
    private String ph_type;
    private String ph_content;
    private Date ph_reg_dt;
    private Integer ph_pi_seq;
}
