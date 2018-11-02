package com.huawei.service.deviceManagement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

/**
 * Query Device Activation Status :
 * This interface is used to query the device activation status based on the device ID.
 * 此接口用于根据设备id查询设备激活状态。
 * After a device logs in to the IoT platform successfully, the IoT platform sets the device state to active.
 * 在设备成功登录到物联网平台之后，物联网平台将设备状态设置为活动状态。 
 */
public class QueryDeviceActivationStatus {

	public static String State(String deviceId) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;

        //please replace the deviceId, when you use the demo.
        //String deviceId = "9a445dda-f62e-4c78-be05-ef0f0c1b447a";
		
        String urlDeviceActivationStatus = Constant.QUERY_DEVICE_ACTIVATION_STATUS + "/" + deviceId;

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse bodyDeviceActivationStatus = httpsUtil.doGetWithParasGetStatusLine(
                urlDeviceActivationStatus, null, header);
        
       /* Map<Object, Object> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(bodyDeviceActivationStatus.getContent(), data.getClass());
        for(Object key : data.keySet()){  
            System.out.println(key+"="+data.get(key));  
        }*/
        
        String result = bodyDeviceActivationStatus.getContent();
        
        return result;
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
