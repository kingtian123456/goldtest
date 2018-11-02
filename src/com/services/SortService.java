package com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dao.CommodityMapper;

import com.dao.SortMapper;
import com.entity.Commodity;
import com.entity.Sort;
import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月29日 下午4:52:31
* 类说明
*/
@Service
public class SortService {
	
	@Resource
	SortMapper stm;
	
	@Resource
	CommodityMapper com;
	
	@Resource
	NewsService nsm;
	
	
	//读子类模版
	public List<Sort> selectBySortSubclass(Integer parentId){
		return stm.selectBySortSubclass(parentId);
	};
	
	//读全部的子类模版
	public List<Sort> selectBySortZiInfo(Map<String, Object> map){
		return stm.selectBySortZiInfo(map);
	};
	
	//读所有的父类模版
	public List<Sort> selectBySortFuInfo(){
		return stm.selectBySortFuInfo();
	};
	
	//修改模版
	public int updateByPrimaryKeySelective(Sort record) {
		return stm.updateByPrimaryKeySelective(record);
	};
	
	//新增分类
	public int insertSelective(Sort record) {
		return stm.insertSelective(record);
	};
	
	//删除分类下的所有商品
	public boolean deleteSortIdCommidty(Integer sortId) {
		boolean mthy = false;
		List<Commodity> list = com.selectBySortIdCommodityInfo(sortId);
		if(list.size() > 0) {//判断该分类下有没有商品
			//有商品，则删除商品
			int num = com.deleteByCommoditSort(sortId);
			if(num > 0) {
				mthy = true;
			}else {
				throw new RuntimeException();
			}
		}else {//没有商品退出
			mthy = true;
		}
		return mthy;
	}
	
	//删除分类及该分类下的所有商品
	public boolean deleteSortCommInfo(Integer sortId,Integer parntId) {
		boolean reult = false;
		int num = stm.deleteByPrimaryKey(sortId);
		if(num > 0) {
			boolean miss = false;
			if(parntId == 1) {
				miss = nsm.deleteSortIdNewsMessage(sortId);
			}else if(parntId == 5) {
				miss = deleteSortIdCommidty(sortId);
			}
			
			if(miss) {
				reult = true;
			}else {
				System.err.println("上级删除出现问题");
			}
		}
		return reult;
	}
	
	//根据分类做数据分页
	public Page<Sort> selectgetSortByInfo(Integer curPage, Integer pageSize) {
		if (curPage == null || pageSize == null) {
	            return null;
	    }
	    int totalRecords = stm.selectBySortZiCount();
	    Page<Sort> page = new Page<>(curPage, pageSize, totalRecords);
	    Map<String,Object> map = new HashMap<>();
	        			   map.put("startIndex",page.getStartIndex());
	        			   map.put("pageSize",page.getPageSize());
	        				   
	    List<Sort> userById = stm.selectBySortZiInfo(map);
	    page.setPageData(userById);

	    return page;
	 }
}
