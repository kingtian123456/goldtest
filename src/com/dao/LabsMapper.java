package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Labs;

public interface LabsMapper {
	
    int deleteByPrimaryKey(Integer labsId);

    int insert(Labs record);

    int insertSelective(Labs record);

    Labs selectByPrimaryKey(Integer labsId);

    int updateByPrimaryKeySelective(Labs record);

    int updateByPrimaryKey(Labs record);
    
    Labs selectByPrimaryNumber(String Number);
    
    List<Labs> selectByPageLabs(Map<String,Object> map);
    
    int selectByLabsCount();
}