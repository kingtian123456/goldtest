package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Download;

public interface DownloadMapper {
	
    int deleteByPrimaryKey(Integer doId);

    int insert(Download record);

    int insertSelective(Download record);

    Download selectByPrimaryKey(Integer doId);

    int updateByPrimaryKeySelective(Download record);

    int updateByPrimaryKey(Download record);
    
    List<Download> selectByDownloadInfo(Map<String,Object> map);
    
    int selectDownloadCount();
}