package com.services;

import java.util.Map;

import javax.annotation.Resource;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.entity.User;
import com.servlert.ToolUtil;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月18日 上午10:29:52
* 类说明
*/
@Service
public class UserService {
	
	@Resource
	UserMapper usmaooer;
	
	//用户登录
	public User selectgetUserOne(Map<String, String> map) {
		return usmaooer.selectByPrimaryOne(map);
	}
	
	//通过用户的手机号码查询密码
	public User selectMessagebackUser(String moble) {
		return usmaooer.MessagebackUser(moble);
	};
	
	//短信找回密码功能
	public String selectputMessageBack(String moble) throws Exception {
		User user = selectMessagebackUser(moble);
		String reuslt = null;
		if(user != null) {
			String msg = "【253云通讯】尊敬的用户您好，您在设备云平台的密码为"+user.getUser_password()+"，请登录平台后及时修改，以免信息泄漏！！！！";
			reuslt = ToolUtil.MessagedateForm(msg,user.getUser_mobile());
			
			JSONObject json = new JSONObject(reuslt);
			
			return json.getString("code");
		}
		
		return null;
	}
	
	//修改用户信息
	public int updateByPrimaryKeySelective(User record) {
		return usmaooer.updateByPrimaryKeySelective(record);
	};
}
