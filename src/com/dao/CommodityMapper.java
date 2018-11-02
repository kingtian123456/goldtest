package com.dao;

import java.util.List;

import com.entity.Commodity;

public interface CommodityMapper {
	
    int deleteByPrimaryKey(Integer comId);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer comId);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);
    
    List<Commodity> selectByCommodityInfo();
    
    int deleteByCommoditSort(Integer sortId);
    
    List<Commodity> selectBySortIdCommodityInfo(Integer sortId);
}