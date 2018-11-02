package com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.DatasMapper;
import com.entity.Chat;
import com.entity.Datas;

import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月30日 上午11:27:48
* 类说明
*/
@Service
public class DatasService{
	
	@Resource
	DatasMapper datas;
	
	public int deleteByPrimaryKey(Integer dsId) {
		
		return datas.deleteByPrimaryKey(dsId);
	}

	
	public int insertSelective(Datas record) {
		
		return datas.insertSelective(record);
	}

	
	public Datas selectByPrimaryKey(Integer dsId) {
		
		return datas.selectByPrimaryKey(dsId);
	}

	
	public int updateByPrimaryKeySelective(Datas record) {
		
		return datas.updateByPrimaryKeySelective(record);
	}
	
	public int deleteByDatasinfo(Map<String,Object> map) {
		return datas.deleteByDatasinfo(map);
	};
	
	//根据设备编号做数据分页
	public Page<Datas> selectgetDatasByInfo(String dev_codes, Integer curPage, Integer pageSize) {
        if (dev_codes == null || curPage == null || pageSize == null) {
            return null;
        }
        int totalRecords = datas.getDatasCount(dev_codes);
        Page<Datas> page = new Page<>(curPage, pageSize, totalRecords);
        Map<String,Object> map = new HashMap<>();
        				   map.put("dev_codes",dev_codes);
        				   map.put("startIndex",page.getStartIndex());
        				   map.put("pageSize",page.getPageSize());
        				   
        List<Datas> userById = datas.getDatasByInfo(map);
        page.setPageData(userById);

        return page;
    }
	
	//根据产品ID查询本日上传数据的记录总数
	public int selectTodayCount(Integer pro_id) {
		
		return datas.selectTodayCount(pro_id);
	};
	
	//根据产品ID查询上传数据的记录总数
	public int selectTotalCount(Integer pro_id) {
		return datas.selectTotalCount(pro_id);
	};
	
	//近20天的图表功能
	public List<Chat> selectByDatasEchar(Integer pro_id){
		return datas.selectByDatasEchar(pro_id);
	};
	
	//按时间生成图表
	public List<Chat> selectByDatasDateEchar(Map<String,Object> map){
		return datas.selectByDatasDateEchar(map);
	};
	
	//按设备编号查询最新的记录
	public Datas selectDatasByNewest(String code) {
		return datas.selectDatasByNewest(code);
	};
	
}
