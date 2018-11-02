package com.services;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DevicesMapper;
import com.entity.Chat;
import com.entity.Devices;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月25日 上午10:54:25
* Devices的服务类
*/
@Service
public class DevicesService {
	
	@Resource
	DevicesMapper device;
	
	//修改设备表的信息
	@Transactional
	public int updateByPrimaryKeySelective(Devices record) {
		return device.updateByPrimaryKeySelective(record);
	};
	
	//新增设备表的信息
	@Transactional
	public int insertSelective(Devices record) {
		return device.insertSelective(record);
	};
	
	//根据设备ID删除设备表的信息
	@Transactional
	public int deleteByPrimaryKey(Integer devId) {
		return device.deleteByPrimaryKey(devId);
	};
	
	public Devices selectByPrimaryKey(Integer devId) {
		return device.selectByPrimaryKey(devId);
	};
	
	//根据产品ID删除设备表的信息
	public int deleteByPrimaryKeyOneDevice(int pro_id) {
		return device.deleteByPrimaryKeyOneDevice(pro_id);
	};
	
	//根据产品ID和用户编号查询设备表的信息
	public List<Devices> selectByProductDev(Map<String,Object> map){
		return device.selectByProductDev(map);
	};
	
	//根据产品ID和用户编号查询设备的总量
	public int selectByProductDevCount(Map<String,Object> map) {
		return device.selectByProductDevCount(map);
	};
	
	//根据设备编号查询设备的信息
	public Devices selectByDeviceOne(Map<String,Object> map) {
		return device.selectByDeviceOne(map);
	};
	
	//根据设备编号查询设备的信息
	public Devices selectByDeviceOne(String dev_code) {
		return device.selectByDeviceOne(dev_code);
	};
	
	//根据设备的MSI码查询设备信息
	public Devices selectByDeviceMSI(Map<String,Object> map) {
		return device.selectByDeviceMSI(map);
	};
	
	//根据产品号查询出在线的设备数
	public int selectByDeviceOnline(int pro_id) {
		return device.selectByDeviceOnline(pro_id);
	};
	
	//查询图表数据
	public List<Chat> selectByDeviceChart(int pro_id){
		return device.selectByDeviceChart(pro_id);
	};
	
	//按日期  生成图表部分
	public List<Chat> selectByDeviceDate(Map<String,Object> map){
		return device.selectByDeviceDate(map);
	};
	
	//按产品查询总记录数
	public List<Chat> selectByDeviceChatzong(int pro_id){
		return device.selectByDeviceChatzong(pro_id);
	};
	
	//按日期  生成图表总数据部分
	public List<Chat> selectByDevicesDateChatZong(Map<String,Object> map){
		return device.selectByDevicesDateChatZong(map);
	};
	
	//查询定位设备
	public List<Devices> selectByDingWeiSheBei(){
		return device.selectByDingWeiSheBei();
	};
	/*
	public Page<Devices> getDevicesByInfo(Integer totalRecord, Integer curPage, Integer pageSize,Map<String,Object> map) {
        if (totalRecord == null || curPage == null || pageSize == null) {
            return null;
        }
        int totalRecords = totalRecord;
        Page<Devices> page = new Page<>(curPage, pageSize, totalRecords);
        
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",page.getPageSize());
        				   
        List<Devices> userById = device.selectByProductDev(map);
        
        page.setPageData(userById);

        return page;
    }*/
}
