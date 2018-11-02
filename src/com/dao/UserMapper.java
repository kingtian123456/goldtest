package com.dao;

import java.util.Map;

import com.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByPrimaryOne(Map<String, String> map);
    
    User MessagebackUser(String moble);
}