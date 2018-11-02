package com.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.ClassfiyMapper;
import com.entity.Classfiy;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月29日 上午10:36:58
* 类说明
*/
@Service
public class ClassfiyService{
	
	
	@Resource
	ClassfiyMapper cm;
	
	public int deleteByPrimaryKey(Integer clasId) {
		
		return cm.deleteByPrimaryKey(clasId);
	}

	
	public int insertSelective(Classfiy record) {
		
		return cm.insertSelective(record);
	}

	
	public Classfiy selectByPrimaryKey(Integer clasId) {
		
		return cm.selectByPrimaryKey(clasId);
	}

	
	public int updateByPrimaryKeySelective(Classfiy record) {
		
		return cm.updateByPrimaryKeySelective(record);
	}

	
	public List<Classfiy> selectByPrimaryinfo() {
		
		return cm.selectByPrimaryinfo();
	}

}
