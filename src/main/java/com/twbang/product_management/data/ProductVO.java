package com.twbang.product_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class ProductVO {
    private Integer pi_seq;
    private Integer pi_si_seq;
    private Integer pi_pci_seq;
    private Integer pi_pmi_seq;
    private Integer pi_ci_seq;
    private String pi_name;
    private String pi_sub;
    private Integer pi_price;
    private Integer pi_like;
    private Integer pi_status;
    private String pi_image;
    private Date pi_reg_dt;
    private Date pi_mod_dt;
    private Integer pi_sell;
    private Integer pi_as;
}
