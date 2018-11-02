package com.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.CommodityMapper;
import com.entity.Commodity;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月5日 上午10:43:39
* 类说明
*/
@Service
public class CommodityService {
	
	@Resource
	CommodityMapper com;
	
	
	//添加单个商品
	public int insertSelective(Commodity record) {
		return com.insertSelective(record);
	};
	
	//查询所有的商品信息
	public List<Commodity> selectByCommodityInfo(){
		return com.selectByCommodityInfo();
	};
	
	//删除单个商品
	public int deleteByPrimaryKey(Integer comId) {
		return com.deleteByPrimaryKey(comId);
	};
	
	//查询单个商品信息
	public Commodity selectByPrimaryKey(Integer comId) {
		return com.selectByPrimaryKey(comId);
	};
	
	//修改单个商品
	public int updateByPrimaryKeySelective(Commodity record) {
		return com.updateByPrimaryKeySelective(record);
	};
	
}
