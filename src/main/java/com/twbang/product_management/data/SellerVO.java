package com.twbang.product_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class SellerVO {
    private Integer si_seq;
    private String si_id;
    private String si_pwd;
    private String si_birth;
    private String si_email;
    private String si_name;
    private String si_address;
    private String si_phone_number;
    private Integer si_status;
    private Date si_reg_dt;
    private Date si_mod_dt;
}
