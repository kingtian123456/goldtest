package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Sort;

public interface SortMapper {
	
    int deleteByPrimaryKey(Integer sortId);

    int insert(Sort record);

    int insertSelective(Sort record);

    Sort selectByPrimaryKey(Integer sortId);

    int updateByPrimaryKeySelective(Sort record);

    int updateByPrimaryKey(Sort record);
    
    List<Sort> selectBySortSubclass(Integer parentId);
    
    List<Sort> selectBySortZiInfo(Map<String, Object> map);
    
    List<Sort> selectBySortFuInfo();
    
    int selectBySortZiCount();
}