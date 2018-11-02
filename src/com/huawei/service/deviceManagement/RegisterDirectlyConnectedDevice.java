package com.huawei.service.deviceManagement;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.entity.Devices;
import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

/**
 * Register Directly Connected Device :
 * This interface is used by NAs to register devices on the IoT platform.
 * NAs使用这个接口在物联网平台上注册设备。
 * After the registration is successful,
 * 注册成功后，
 * the IoT platform allocates a device ID for the device,which is used as the unique identifier of the device.
 * 物联网平台为设备分配设备ID，它被用作设备的唯一标识符。
 * Unregistered devices are not allowed to access the IoT platform.
 * 未注册的设备不允许访问物联网平台。
 * In NB-IoT scenarios, the Set device info interface needs to be invoked to set device information after the registration is successful.
 * 在NB物联网场景中，在注册成功后，需要设置设备信息接口来设置设备信息。
 */
public class RegisterDirectlyConnectedDevice {

	public static String Adddevice(String nodeId) throws Exception{

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;
		String urlReg = Constant.REGISTER_DEVICE;

        //please replace the verifyCode and nodeId and timeout, when you use the demo.  
		//IMEI number:
        //String verifyCode = "863703031123297";
		String verifyCode = nodeId; 
        Integer timeout = 0;
      
        Map<String, Object> paramReg = new HashMap<>();
        paramReg.put("verifyCode", verifyCode.toUpperCase());
        paramReg.put("nodeId", nodeId.toUpperCase());
        paramReg.put("timeout", timeout);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);
        
        Map<String, String> head = new HashMap<>();
        
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseReg = httpsUtil.doPostJsonGetStatusLine(urlReg, header, jsonRequest);
        
        String result = responseReg.getContent();
        
        return result;
        
    }
	
	public static void main(String[] args)throws Exception {
		System.err.println(Adddevice("863703035590897"));
		JSONObject json = new JSONObject(Adddevice("863703035590897"));
		Devices dev = new Devices();
		dev.setDev_code(json.getString("deviceId"));
		dev.setDev_name("GPStest");
		ModifyDeviceInfo.Modification(dev);
	}

    /**
     * Authentication，get token
     * 权鉴接口
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

        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
        return data.get("accessToken");
    }

}
