package com.huawei.service.deviceManagement;

import java.util.HashMap;
import java.util.Map;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

/**
 * Delete Directly Connected Device :
 * 删除直接连接的设备
 * This interface is used to delete a device.
 * 此接口用于删除设备。
 */
public class DeleteDirectlyConnectedDevice {


	public static void DeletingEequipment(String deviceId) throws Exception {

		// Two-Way Authentication
		HttpsUtil httpsUtil = new HttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();

		// Authentication，get token
		String accessToken = login(httpsUtil);

		//Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;

		//please replace the deviceId, when you use the demo. 
        String urlDelete = Constant.DELETE_DEVICE + "/" +deviceId;
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseDelete = httpsUtil.doDeleteGetStatusLine(urlDelete, header);
        
    }
	
	public static void main(String[] args) throws Exception{
		DeletingEequipment("eaa2cd7f-29d9-4fe3-ad96-579264d847a5");
	}

	/**
	 * Authentication，get token
	 * */
	@SuppressWarnings("unchecked")
	public static String login(HttpsUtil httpsUtil) throws Exception {

		String appId = Constant.APPID;
		String secret = Constant.SECRET;
		String urlLogin = Constant.APP_AUTH;

		Map<String, String> paramLogin = new HashMap<>();
		paramLogin.put("appId", appId);
		paramLogin.put("secret", secret);

		StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);

		System.out.println("app auth success,return accessToken:");
		System.out.print(responseLogin.getStatusLine());
		System.out.println(responseLogin.getContent());
		System.out.println();

		Map<String, String> data = new HashMap<>();
		data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
		return data.get("accessToken");
	}

}