package com.twbang.product_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class BuyerVO {
    private Integer bi_seq;
    private String bi_name;
    private String bi_id;
    private String bi_pwd;
    private String bi_email;
    private String bi_phone_number;
    private String bi_birth;
    private String bi_address;
    private Integer bi_status;
    private Date bi_reg_dt;
    private Date bi_mod_dt;
}
