package com.dao;

import java.util.List;

import com.entity.Place;

public interface PlaceMapper {
    int deleteByPrimaryKey(Integer plid);

    int insert(Place record);

    int insertSelective(Place record);

    Place selectByPrimaryKey(Integer plid);

    int updateByPrimaryKeySelective(Place record);

    int updateByPrimaryKey(Place record);
    
    List<Place> selectByPrThelatestKey();
    
    List<Place> selectByDateTodayPlace(String plcode);
    
    List<Place> selectByNearlyonehour(String plcode);
}