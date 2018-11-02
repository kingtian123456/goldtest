package com.dao;

import java.util.List;
import java.util.Map;
import com.entity.News;

public interface NewsMapper {
	
    int deleteByPrimaryKey(Integer newsId);
    
    int deleteByNewsSort(Integer sortId);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer newsId);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);
    
    int selectByblogCount(Integer sortid);
    
    List<News> selectByblogpaging(Map<String,Object> map);
    
    List<News> selectBySortIdNews(Integer sortId);
    
    News selectByNewsID(String title);
    
    List<News> selectByNewsArticle();
    
    List<String> selectByNewsKey();
}