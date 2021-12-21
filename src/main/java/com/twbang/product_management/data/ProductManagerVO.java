package com.twbang.product_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class ProductManagerVO {
    private Integer pmi_seq;
    private Integer pmi_pci_seq;
    private String pmi_id;
    private String pmi_pwd;
    private String pmi_name;
    private String pmi_email;
    private String pmi_birth;
    private String pmi_phone_number;
    private Integer pmi_status;
    private Date pmi_reg_dt;
    private Date pmi_mod_dt;
    private String category_name;
}
