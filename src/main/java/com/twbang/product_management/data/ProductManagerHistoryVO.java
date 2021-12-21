package com.twbang.product_management.data;

import lombok.Data;

@Data
public class ProductManagerHistoryVO {
    private Integer pmh_seq;
    private String pmh_type;
    private String pmh_content;
    private Integer pmh_pci_seq;
}
