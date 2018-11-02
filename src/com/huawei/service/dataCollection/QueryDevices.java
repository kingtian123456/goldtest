package com.huawei.service.dataCollection;

import java.util.HashMap;
import java.util.Map;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

/**
 * Query All Devices:
 * This interface is used to query information about devices in batches.
 * 此接口用于批量查询有关设备的信息。
 */
public class QueryDevices {

    public static String BatchQuery(String appId) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        //String appId = Constant.APPID;
        String urlQueryDevices = Constant.QUERY_DEVICES;

        //please replace the pageNo and pageSize, when you use the demo.
        Integer pageNo = 0;
        Integer pageSize = 10;

        Map<String, String> paramQueryDevices = new HashMap<>();
        paramQueryDevices.put("appId", appId);
        paramQueryDevices.put("pageNo", pageNo.toString());
        paramQueryDevices.put("pageSize", pageSize.toString());

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse bodyQueryDevices = httpsUtil.doGetWithParasGetStatusLine(urlQueryDevices,
                paramQueryDevices, header);

        /*System.out.println("QueryDevices, response content:");
        System.out.print(bodyQueryDevices.getStatusLine());
        System.out.println(bodyQueryDevices.getContent());
        System.out.println();*/
        String result = bodyQueryDevices.getContent();
        
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
