package com.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.AgreementMapper;
import com.entity.Agreement;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月29日 上午10:33:33
* 类说明
*/
@Service
public class AgreementService{
	
	@Resource
	AgreementMapper atm;
	
	public int deleteByPrimaryKey(Integer atId) {
		
		return atm.deleteByPrimaryKey(atId);
	}

	
	public int insertSelective(Agreement record) {
		
		return atm.insertSelective(record);
	}

	
	public Agreement selectByPrimaryKey(Integer atId) {
		
		return atm.selectByPrimaryKey(atId);
	}

	
	public int updateByPrimaryKeySelective(Agreement record) {
		
		return atm.updateByPrimaryKeySelective(record);
	}

	
	public List<Agreement> selectByPrimaryinfo() {
		
		return atm.selectByPrimaryinfo();
	}
	
}
