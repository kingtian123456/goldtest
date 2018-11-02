package com.huawei.testMessagePush;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSubscribeAllNotification {

	@SuppressWarnings("resource")
    public static void main(String args[]) throws Exception {

	    SimpleHttpServer.startServer(8888); // start server to receive message

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
	    
		String accessToken = login(httpsUtil);
		String appId = Constant.APPID; // please replace the appId, when you use the demo.
        String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION; // 订阅的接口 please replace the IP and Port of BASE_URL, when you use the demo.

        String callbackurl = NotifyType.TEST_CALLBACK_BASE_URL; // 回调的 地址 please replace the IP and Port of BASE_URL, when you use the demo.
        
        /*
         * na to subscribe notification from the IoT platform
         * notifyTypes: 
         * bindDevice/serviceInfoChanged/deviceInfoChanged/deviceDataChanged/deviceAdded/deviceDeleted
         * messageConfirm/commandRsp/deviceEvent/appDeleted/ruleEvent/deviceDatasChanged
         */
        List<String> notifyTypes = NotifyType.getNotifyTypes();
        for (String notifyType : notifyTypes) {
            
            Map<String, Object> paramSubscribe = new HashMap<>();
            paramSubscribe.put("notifyType", notifyType);
            paramSubscribe.put("callbackurl", callbackurl);
            
            String jsonRequest = JsonUtil.jsonObj2Sting(paramSubscribe);
            
            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, appId);
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            
            HttpResponse httpResponse = httpsUtil.doPostJson(urlSubscribe, header, jsonRequest);
            
            String bodySubscribe = httpsUtil.getHttpResponseBody(httpResponse);
            
            System.out.println("SubscribeNotification, notifyType:" + notifyType + ", callbackurl:" + callbackurl +", response content:");
            System.out.print(httpResponse.getStatusLine());
            System.out.println(bodySubscribe);
            System.out.println();
            
        }
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