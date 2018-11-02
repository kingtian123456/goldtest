package com.huawei.service.dataCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

/**
 * Query Device History Data :
 * This interface is used by NAs to query historical device data.
 * NAs使用此接口查询历史设备数据。
 */
public class QueryDeviceHistoryData {

    @SuppressWarnings("unchecked")
	public static List select(String deviceId) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryDeviceHistoryData = Constant.QUERY_DEVICE_HISTORY_DATA;

        //please replace the deviceId and gatewayId, when you use the demo.
        //String deviceId = "f511684c-87cf-4903-8a44-f2c501daaa6b";
        String gatewayId = deviceId;

        Map<String, String> paramQueryDeviceHistoryData = new HashMap<>();
        paramQueryDeviceHistoryData.put("deviceId", deviceId);
        paramQueryDeviceHistoryData.put("gatewayId", gatewayId);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse bodyQueryDeviceHistoryData = httpsUtil.doGetWithParasGetStatusLine(
                urlQueryDeviceHistoryData, paramQueryDeviceHistoryData, header);

        //System.out.println("QueryDeviceHistoryData, response content:");
        //System.out.print(bodyQueryDeviceHistoryData.getStatusLine());
        //System.out.println(bodyQueryDeviceHistoryData.getContent());
        //System.out.println();
        
        Map<String, Object> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(bodyQueryDeviceHistoryData.getContent(), data.getClass());
        
        System.out.println(data.toString());
        
        List<Map> list = (List<Map>)data.get("deviceDataHistoryDTOs");
        
        return  list;
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
