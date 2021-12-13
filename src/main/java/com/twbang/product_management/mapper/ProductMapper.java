package com.twbang.product_management.mapper;

import java.util.List;

import com.twbang.product_management.data.ProductVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    public List<ProductVO> getProductInfo(Integer offset);
    public Integer getProductCount();
    public void addProduct(ProductVO data);
    public void deleteProduct(Integer seq);
}
