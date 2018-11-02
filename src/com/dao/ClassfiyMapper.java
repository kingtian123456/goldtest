package com.dao;

import java.util.List;

import com.entity.Classfiy;

public interface ClassfiyMapper {
    int deleteByPrimaryKey(Integer clasId);

    int insert(Classfiy record);

    int insertSelective(Classfiy record);

    Classfiy selectByPrimaryKey(Integer clasId);

    int updateByPrimaryKeySelective(Classfiy record);

    int updateByPrimaryKey(Classfiy record);
    
    List<Classfiy> selectByPrimaryinfo();
}