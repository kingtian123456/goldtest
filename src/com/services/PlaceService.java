package com.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.PlaceMapper;
import com.entity.Place;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月8日 上午9:36:40
* 类说明
*/
@Service
public class PlaceService {
	
		@Resource
		PlaceMapper pm;
		
		public int deleteByPrimaryKey(Integer plid) {
			return pm.deleteByPrimaryKey(plid);
		};

		public int insertSelective(Place record) {
			return pm.insertSelective(record);
		};

		public Place selectByPrimaryKey(Integer plid) {
			return pm.selectByPrimaryKey(plid);
		};

		public int updateByPrimaryKeySelective(Place record) {
			return pm.updateByPrimaryKeySelective(record);
		};
		
		//查询最新的设备数据
		public List<Place> selectByPrThelatestKey(){
			return pm.selectByPrThelatestKey();
		};
		
		//根据设备编号查询今天的数据记录
		public List<Place> selectByDateTodayPlace(String plcode){
			return pm.selectByDateTodayPlace(plcode);
		};
		
		//根据设备编号查询今天近一小时内的数据记录
		public List<Place> selectByNearlyonehour(String plcode){
			return pm.selectByNearlyonehour(plcode);
		};

}
