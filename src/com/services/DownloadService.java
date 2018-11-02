package com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dao.DownloadMapper;

import com.entity.Download;
import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月23日 上午9:19:19
* 类说明
*/
@Service
public class DownloadService {
	
	
	@Resource
	DownloadMapper dlm;
	
	//删除单个文件信息
	public int deleteByPrimaryKey(Integer doId) {
		return dlm.deleteByPrimaryKey(doId);
	};
	
	//插入单个文件信息
	public int insertSelective(Download record) {
		return dlm.insertSelective(record);
	};
	
	//修改文件信息
	public int updateByPrimaryKeySelective(Download record) {
		return dlm.updateByPrimaryKeySelective(record);
	};
	
	//查询文件信息，分页
	public Page<Download> selectgetDownloadByInfo(Integer curPage, Integer pageSize) {
        if (curPage == null || pageSize == null) {
            return null;
        }
        int totalRecords = dlm.selectDownloadCount();
        Page<Download> page = new Page<>(curPage, pageSize, totalRecords);
        Map<String,Object> map = new HashMap<>();
        				   map.put("startIndex",page.getStartIndex());
        				   map.put("pageSize",page.getPageSize());
        				   
        List<Download> userById = dlm.selectByDownloadInfo(map);
        page.setPageData(userById);

        return page;
    }

}
