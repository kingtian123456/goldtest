package com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductMapper;
import com.entity.Agreement;
import com.entity.Product;
import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月25日 上午10:39:03
* Product类的服务层
*/
@Service
public class ProductService {
	
	@Resource
	ProductMapper pdt;
	
	@Resource
	AgreementService atm;
	
	@Resource
	ClassfiyService clf;
	
	//修改产品表的内容
	@Transactional
	public int updateByPrimaryKeySelective(Product record) {
		return pdt.updateByPrimaryKeySelective(record);
	};
	
	//新增产品表的内容
	@Transactional
	public int insertSelective(Product record) {
		return pdt.insertSelective(record);
	};
	
	//查询所有的产品信息
	public List<Product> selectBymaryinfo(Map<String,Object> map){
		List<Product> list = pdt.selectBymaryinfo(map);
		for (Product product : list) {
			Agreement ment = atm.selectByPrimaryKey(product.getPro_contype());
			product.setAment(ment);
			
		} 
		 return list;
	};
	
	//查询所有的产品信息(级联查询)
	public List<Product> selectByProductall(Map<String,Object> map){
		return pdt.selectByProductall(map);
	};
	
	//查询该用户下的产品总数
	public int selectBymaryCount(Map<String,Object> map) {
		return pdt.selectBymaryCount(map);
	}
	
	//根据ID  查询产品
	public Product selectByPrimaryKey(Integer proId) {
		Product product =  pdt.selectByPrimaryKey(proId);
		product.setAment(atm.selectByPrimaryKey(product.getPro_contype()));
		product.setFiy(clf.selectByPrimaryKey(product.getPro_devb()));
		return product;
	};
	
	//根据ID  删除产品
	public int deleteByPrimaryKey(Integer proId) {
		return pdt.deleteByPrimaryKey(proId);
	};
	
	//根据设备编号做数据分页
	public Page<Product> selectgetDatasByInfo(String code, Integer curPage, Integer pageSize) {
	    if (code == null || curPage == null || pageSize == null) {
	          return null;
	    }
	    Map<String,Object> map = new HashMap<>();
		   					map.put("code",code);
	    int totalRecords = selectBymaryCount(map);
	    Page<Product> page = new Page<>(curPage, pageSize, totalRecords);
	    map.put("before",page.getStartIndex());    		
	    map.put("after",page.getPageSize());
	        				   
	    List<Product> userById = selectBymaryinfo(map);
	    page.setPageData(userById);

	    return page;
	}
}
