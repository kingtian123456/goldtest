package com.huawei.service.dataCollection;

import java.util.HashMap;
import java.util.Map;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

/**
 * Query Device Capability :
 * This interface is used to query the service capability of a device.
 * 此接口用于查询设备的服务能力。
 */
public class QueryDeviceCapabilities {

	public static String Service(String deviceId) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryDeviceCapabilities = Constant.QUERY_DEVICE_CAPABILITIES;

        //please replace the deviceId and gatewayId, when you use the demo.
        //String deviceId = "9a445dda-f62e-4c78-be05-ef0f0c1b447a";
        String gatewayId = deviceId;

        Map<String, String> paramQueryDeviceCapabilities = new HashMap<>();
        paramQueryDeviceCapabilities.put("deviceId", deviceId);
        paramQueryDeviceCapabilities.put("gatewayId", gatewayId);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse bodyQueryDeviceCapabilities = httpsUtil.doGetWithParasGetStatusLine(
                urlQueryDeviceCapabilities, paramQueryDeviceCapabilities, header);

        /*System.out.println("QueryDeviceCapabilities, response content:");
        System.out.print(bodyQueryDeviceCapabilities.getStatusLine());
        System.out.println(bodyQueryDeviceCapabilities.getContent());
        System.out.println();*/
        String result = bodyQueryDeviceCapabilities.getContent();
        
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
