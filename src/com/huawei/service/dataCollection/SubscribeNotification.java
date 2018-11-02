package com.huawei.service.dataCollection;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

/**
 * Subscribe Device Change Info :
 * This interface is used by NAs to subscribe to device change.
 * NAs使用此接口订阅设备更改。
 * When device information is changed, the IoT platform sends a notification to NAs.
 * 当设备信息被更改时，物联网平台向NAs发送通知
 */
public class SubscribeNotification {

    public static void Subscribe() throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;
        

        /*添加新设备 通知的订阅
         * subscribe deviceAdded notification
         * http://localhost:8080/manholeCover/deviceAdded
         */
        String callbackurl_deviceAdded = Constant.DEVICE_ADDED_CALLBACK_URL;
        String notifyType_deviceAdded = Constant.DEVICE_ADDED;

        Map<String, Object> paramSubscribe = new HashMap<>();
        paramSubscribe.put("notifyType", notifyType_deviceAdded);
        paramSubscribe.put("callbackurl", callbackurl_deviceAdded);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramSubscribe);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse = httpsUtil.doPostJson(urlSubscribe, header, jsonRequest);

        String bodySubscribe = httpsUtil.getHttpResponseBody(httpResponse);

        System.out.println("SubscribeNotification: " + notifyType_deviceAdded + ", response content:");
        System.out.print(httpResponse.getStatusLine());
        System.out.println(bodySubscribe);
        System.out.println();
        
        
        /*设备信息变化订阅
         * subscribe deviceInfoChanged notification
         */
        String callbackurl_deviceInfoChanged = Constant.DEVICE_INFO_CHANGED_CALLBACK_URL;
        String notifyType_deviceInfoChanged = Constant.DEVICE_INFO_CHANGED;

        Map<String, Object> paramSubscribe_deviceInfoChanged = new HashMap<>();
        paramSubscribe_deviceInfoChanged.put("notifyType", notifyType_deviceInfoChanged);
        paramSubscribe_deviceInfoChanged.put("callbackurl", callbackurl_deviceInfoChanged);

        String jsonRequest_deviceInfoChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceInfoChanged);

        Map<String, String> header_deviceInfoChanged = new HashMap<>();
        header_deviceInfoChanged.put(Constant.HEADER_APP_KEY, appId);
        header_deviceInfoChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceInfoChanged = httpsUtil.doPostJson(urlSubscribe, header_deviceInfoChanged, jsonRequest_deviceInfoChanged);

        String bodySubscribe_deviceInfoChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceInfoChanged);

        /*System.out.println("SubscribeNotification: " + notifyType_deviceInfoChanged + ", response content:");
        System.out.print(httpResponse_deviceInfoChanged.getStatusLine());
        System.out.println(bodySubscribe_deviceInfoChanged);
        System.out.println();
        
        
        /*设备数据变化 订阅
         * subscribe deviceDataChanged notification
         */
        String callbackurl_deviceDataChanged = Constant.DEVICE_DATA_CHANGED_CALLBACK_URL;
        String notifyType_deviceDataChanged = Constant.DEVICE_DATA_CHANGED;

        Map<String, Object> paramSubscribe_deviceDataChanged = new HashMap<>();
        paramSubscribe_deviceDataChanged.put("notifyType", notifyType_deviceDataChanged);
        paramSubscribe_deviceDataChanged.put("callbackurl", callbackurl_deviceDataChanged);

        String jsonRequest_deviceDataChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDataChanged);

        Map<String, String> header_deviceDataChanged = new HashMap<>();
        header_deviceDataChanged.put(Constant.HEADER_APP_KEY, appId);
        header_deviceDataChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceDataChanged = httpsUtil.doPostJson(urlSubscribe, header_deviceDataChanged, jsonRequest_deviceDataChanged);

        String bodySubscribe_deviceDataChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDataChanged);

       /* System.out.println("SubscribeNotification: " + notifyType_deviceDataChanged + ", response content:");
        System.out.print(httpResponse_deviceDataChanged.getStatusLine());
        System.out.println(bodySubscribe_deviceDataChanged);
        System.out.println();
        
        
        /*删除设备 通知 订阅
         * subscribe deviceDeleted notification
         */
        String callbackurl_deviceDeleted = Constant.DEVICE_DELETED_CALLBACK_URL;
        String notifyType_deviceDeleted = Constant.DEVICE_DELETED;

        Map<String, Object> paramSubscribe_deviceDeleted = new HashMap<>();
        paramSubscribe_deviceDeleted.put("notifyType", notifyType_deviceDeleted);
        paramSubscribe_deviceDeleted.put("callbackurl", callbackurl_deviceDeleted);

        String jsonRequest_deviceDeleted = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDeleted);

        Map<String, String> header_deviceDeleted = new HashMap<>();
        header_deviceDeleted.put(Constant.HEADER_APP_KEY, appId);
        header_deviceDeleted.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceDeleted = httpsUtil.doPostJson(urlSubscribe, header_deviceDeleted, jsonRequest_deviceDeleted);

        String bodySubscribe_deviceDeleted = httpsUtil.getHttpResponseBody(httpResponse_deviceDeleted);

        /*System.out.println("SubscribeNotification: " + notifyType_deviceDeleted + ", response content:");
        System.out.print(httpResponse_deviceDeleted.getStatusLine());
        System.out.println(bodySubscribe_deviceDeleted);
        System.out.println();
		
		设备信息 通知 订阅
		subscribe serviceInfoChanged notification
         */
        String callbackurl_serviceInfoChanged = Constant.SERVICE_INFO_CHANGED_CALLBACK_URL;
        String notifyType_serviceInfoChanged = Constant.SERVICE_INFO_CHANGED;

        Map<String, Object> paramSubscribeserviceInfoChanged = new HashMap<>();
        paramSubscribeserviceInfoChanged.put("notifyType", notifyType_serviceInfoChanged);
        paramSubscribeserviceInfoChanged.put("callbackurl", callbackurl_serviceInfoChanged);

        String jsonRequestserviceInfoChanged = JsonUtil.jsonObj2Sting(paramSubscribeserviceInfoChanged);

        Map<String, String> headerserviceInfoChanged = new HashMap<>();
        headerserviceInfoChanged.put(Constant.HEADER_APP_KEY, appId);
        headerserviceInfoChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponseserviceInfoChanged = httpsUtil.doPostJson(urlSubscribe, headerserviceInfoChanged, jsonRequestserviceInfoChanged);

        String bodySubscribeserviceInfoChanged = httpsUtil.getHttpResponseBody(httpResponseserviceInfoChanged);

        System.out.println("SubscribeNotification: " + notifyType_serviceInfoChanged + ", response content:");
        System.out.print(httpResponseserviceInfoChanged.getStatusLine());
        System.out.println(bodySubscribeserviceInfoChanged);
        System.out.println();
        
        
        /*设备数据批量变化订阅
         * subscribe deviceDatasChanged notification
         */
        String callbackurl_deviceDatasChanged = Constant.DEVICE_DATAS_CHANGED_CALLBACK_URL;
        String notifyType_deviceDatasChanged = Constant.DEVICE_DATAS_CHANGED;

        Map<String, Object> paramSubscribe_deviceDatasChanged = new HashMap<>();
        paramSubscribe_deviceDatasChanged.put("notifyType", notifyType_deviceDatasChanged);
        paramSubscribe_deviceDatasChanged.put("callbackurl", callbackurl_deviceDatasChanged);

        String jsonRequest_deviceDatasChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDatasChanged);

        Map<String, String> header_deviceDatasChanged = new HashMap<>();
        header_deviceDatasChanged.put(Constant.HEADER_APP_KEY, appId);
        header_deviceDatasChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceDatasChanged = httpsUtil.doPostJson(urlSubscribe, header_deviceDatasChanged, jsonRequest_deviceDatasChanged);

        String bodySubscribe_deviceDatasChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDatasChanged);

       /* System.out.println("SubscribeNotification: " + notifyType_deviceDatasChanged + ", response content:");
        System.out.print(httpResponse_deviceDatasChanged.getStatusLine());
        System.out.println(bodySubscribe_deviceDatasChanged);
        System.out.println();*/
        
    }

    public static void main(String[] args) throws Exception {
    	Subscribe();
	}
    
    // Authentication，get token
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
