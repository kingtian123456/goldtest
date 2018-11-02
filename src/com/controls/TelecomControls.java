package com.controls;

import java.util.Date;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Place;
import com.huawei.utils.Constant;
import com.services.PlaceService;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月7日 上午10:53:28
* 类说明
*/
@Controller
public class TelecomControls {
	
		@Resource
		PlaceService ps;
		
		// 注册设备是否成功的URL
		@RequestMapping(value = "/deviceAdded" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public String recvAddDeviceNotify(@RequestBody String str){
			//value = Constant.DEVICE_ADDED_CALLBACK_URL
				System.out.println("进入了注册设备返回post方法");
				System.out.println(str);
				String result = null;
				try {
					JSONObject jbj = new JSONObject(str);
					
					String resultCode = jbj.getString(Constant.resultCode);
					
					if(resultCode != null) {
						result = "注册设备成功";
					}else {
						result = "注册设备失败";
					}
					System.out.println(result);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return result;
	    }
		
		// 设备信息变化，回调的URL
		@RequestMapping(value = "/deviceInfoChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public String equipmentNotice(@RequestBody String str){
			System.out.println("进入了post方法");
			System.out.println(str);
			String result = null;
			try {
				JSONObject jbj = new JSONObject(str);
				
				JSONObject js = jbj.getJSONObject(Constant.deviceInfo);
				
				if(js.toString() != null) {
					result = "修改设备信息成功";
				}else {
					result = "修改设备信息失败";
				}
				
				System.out.println(result);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		
		//设备动态数据变化，回调的URL
		@RequestMapping(value = "/deviceDataChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public void Datachange(@RequestBody String str){
			System.out.println("进入了设备动态数据回执post方法");
			System.out.println(str);
			Place place = null;
			try {
				place = new Place();
				JSONObject jsonobject = new JSONObject(str);
				place.setPlcode(jsonobject.getString("deviceId"));
				if(jsonobject.has("service")) {
					JSONObject jser = jsonobject.getJSONObject("service");
					if(jser.has("data")) {
						JSONObject jsdata = jser.getJSONObject("data");
						place.setPlvalue(jsdata.toString());
						place.setPlintime(new Date());
					}
				}
				
			ps.insertSelective(place);
			
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		
		//删除设备，回调的URL
		@RequestMapping(value = "/deviceDeleted", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public String DeletingEquipment(@RequestBody String str){
			System.out.println("进入了post方法");
			System.out.println(str);
			String result = null;
			try {
				JSONObject jbj = new JSONObject(str);
				
				String js = jbj.getString(Constant.deviceId);
				
				if(js != null) {
					result = "删除设备成功";
				}else {
					result = "删除设备失败";
				}
				
				System.out.println(result);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}
}
