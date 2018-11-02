package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Chat;
import com.entity.Devices;

public interface DevicesMapper {
    int deleteByPrimaryKey(Integer devId);

    int insert(Devices record);

    int insertSelective(Devices record);

    Devices selectByPrimaryKey(Integer devId);

    int updateByPrimaryKeySelective(Devices record);

    int updateByPrimaryKey(Devices record);
    
    int deleteByPrimaryKeyOneDevice(int pro_id);
    
    List<Devices> selectByProductDev(Map<String,Object> map);
    
    int selectByProductDevCount(Map<String,Object> map);
    
    Devices selectByDeviceOne(Map<String,Object> map);
    
    Devices selectByDeviceMSI(Map<String,Object> map);
    
    int selectByDeviceOnline(int pro_id);
    
    List<Chat> selectByDeviceChart(int pro_id);
    
    List<Chat> selectByDeviceDate(Map<String,Object> map);
    
    List<Chat> selectByDeviceChatzong(int pro_id);
    
    List<Chat> selectByDevicesDateChatZong(Map<String,Object> map);
    
    Devices selectByDeviceOne(String dev_code);
    
    List<Devices> selectByDingWeiSheBei();
}