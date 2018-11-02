package com.huawei.service.deviceManagement;

import java.util.HashMap;
import java.util.Map;

import com.entity.Devices;
import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

/**
 * Set device information:
 * This interface is used by an NA to set or modify device information.
 * 使用此接口设置或修改设备信息。
 */
public class ModifyDeviceInfo {

	public static void Modification(Devices dece) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;

        //please replace the deviceId, when you use the demo.
        //String deviceId = "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
		String deviceId = dece.getDev_code();
        String urlModifyDeviceInfo = Constant.MODIFY_DEVICE_INFO + "/" + deviceId;

        //please replace the following parameter values, when you use the demo.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        //The following parameter values of this demo are use the watermeter profile that already initialized to IoT platform.

        Map<String, Object> paramModifyDeviceInfo = new HashMap<>();
        //paramModifyDeviceInfo.put("manufacturerName", dece.getDev_name());
        //paramModifyDeviceInfo.put("deviceType", dece.getDev_type());
        //paramModifyDeviceInfo.put("location", dece.getDevMac());
        paramModifyDeviceInfo.put("name", dece.getDev_name());
        paramModifyDeviceInfo.put("protocolType","COAP");
        paramModifyDeviceInfo.put("deviceType","gps");
        paramModifyDeviceInfo.put("model","gps");
        paramModifyDeviceInfo.put("manufacturerId","zchs");
        paramModifyDeviceInfo.put("manufacturerName","zchs");
        paramModifyDeviceInfo.put("location","Shenzhen");
        //paramModifyDeviceInfo.put("region", dece.getDevAddress());
        

        String jsonRequest = JsonUtil.jsonObj2Sting(paramModifyDeviceInfo);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo,
                header, jsonRequest);
    }
	
	public static void main(String[] args)throws Exception {
		Devices dev = new Devices();
				dev.setDev_code("525b2039-b5bb-4bed-aa26-5e80115477ad");
				dev.setDev_name("GPStest");
		Modification(dev);
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