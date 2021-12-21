package com.twbang.product_management.mapper;

import java.util.List;

import com.twbang.product_management.data.ProductCategoryVO;
import com.twbang.product_management.data.ProductManagerVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductManagerMapper {
    public void addProductManagerInfo(ProductManagerVO data);
    public List<ProductManagerVO> getProductManagerList(String type, String keyword, Integer offset);
    public Integer getProductManagerCnt(String type, String keyword);
    public List<ProductCategoryVO> getCategoryByKeyword(String keyword);
    public void deleteProductManager(Integer seq);
    public ProductManagerVO getProductManagerInfoBySeq(Integer seq);
    public void updateProductManager(ProductManagerVO data);
}
