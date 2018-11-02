package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Product;

public interface ProductMapper {
	
    int deleteByPrimaryKey(Integer proId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer proId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    List<Product> selectBymaryinfo(Map<String,Object> map);
    
    int selectBymaryCount(Map<String,Object> map);
    
    List<Product> selectByProductall(Map<String,Object> map);
}