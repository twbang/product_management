package com.twbang.product_management.mapper;

import java.util.List;

import com.twbang.product_management.data.ProductCategoryVO;
import com.twbang.product_management.data.ProductHistoryVO;
import com.twbang.product_management.data.ProductVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    public List<ProductVO> getProductInfo(Integer offset, String keyword);

    public Integer getProductCount(String keyword);

    public void addProduct(ProductVO data);

    public List<ProductCategoryVO> getCategoryByKeyword(String keyword);

    public void deleteProduct(Integer seq);

    public ProductVO getProductInfoBySeq(Integer seq);

    public void updateProduct(ProductVO data);

    public Integer selectLatestDataSeq();

    public void insertProductHistory(ProductHistoryVO data);

    public Integer isExistProduct(Integer seq);
}
