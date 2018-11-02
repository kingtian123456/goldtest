package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Chat;
import com.entity.Datas;

public interface DatasMapper {
	
    int deleteByPrimaryKey(Integer dsId);

    int insertSelective(Datas record);

    Datas selectByPrimaryKey(Integer dsId);

    int updateByPrimaryKeySelective(Datas record);
    
    int deleteByDatasinfo(Map<String,Object> map);
    
    List<Datas> getDatasByInfo(Map<String,Object> map);
    
    int getDatasCount(String dev_codes);
    
    int selectTodayCount(Integer pro_id);
    
    int selectTotalCount(Integer pro_id);
    
    List<Chat> selectByDatasEchar(Integer pro_id);
    
    List<Chat> selectByDatasDateEchar(Map<String,Object> map);
    
    Datas selectDatasByNewest(String code);
}