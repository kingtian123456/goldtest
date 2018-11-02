package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer msId);
    
    int delectByNewsMage(Integer newsId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer msId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    
    List<Message> selectByMessageNewsOne(Map<String,Object> map);
    
    int selectByMessageNewsCount(Map<String,Object> map);
    
    List<Message> selectByMessageLevel(Integer ayerid);
    
    int selectByMessageNewsIdCount(Integer newsId);
}