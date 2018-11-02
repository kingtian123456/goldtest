package com.dao;

import java.util.List;

import com.entity.Agreement;

public interface AgreementMapper {
    int deleteByPrimaryKey(Integer atId);

    int insert(Agreement record);

    int insertSelective(Agreement record);

    Agreement selectByPrimaryKey(Integer atId);

    int updateByPrimaryKeySelective(Agreement record);

    int updateByPrimaryKey(Agreement record);
    
    List<Agreement> selectByPrimaryinfo();
}