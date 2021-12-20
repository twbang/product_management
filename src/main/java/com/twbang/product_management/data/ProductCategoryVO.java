package com.twbang.product_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class ProductCategoryVO {
    private Integer pci_seq;
    private String pci_name;
    private Date pci_reg_dt;
}
