/*
 * File Name: com.huawei.service.signalingDelivery.CreateDeviceCmdCancelTaskV4.java
 *
 * Copyright Notice:
 *      Copyright  1998-2008, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.huawei.service.signalingDelivery;

import java.util.HashMap;
import java.util.Map;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

/**
 * This interface is used by NAs to create batch task,
 * which is used to Cancel all device commands under the specified device ID.
 * NAs使用此接口创建批处理任务，
 *用于取消指定设备ID下的所有设备命令。
 */
public class CreateDeviceCmdCancelTaskV4 {

    public static void downsideData(String devid,String num) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlUpdateAsynCommand = Constant.CREATE_DEVICECMD_CANCEL_TASK;

        //please replace the deviceId, when you use the demo.
        String deviceId  = devid;
        
        Map<String, Object> downside = new HashMap<String, Object>();
        					downside.put("ReportCycle",num);
        					downside.put("ReportMode","0");
        
        Map<String, Object> reult = new HashMap<String, Object>();
        reult.put("serviceId","GpsData");
        reult.put("method", "SET_REPORT_CYCLE_CMD");
        reult.put("paras", downside);
        
        Map<String, Object> paraCreateDeviceCmdCancelTask = new HashMap<>();
        paraCreateDeviceCmdCancelTask.put("deviceId", deviceId);
        paraCreateDeviceCmdCancelTask.put("command",reult);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paraCreateDeviceCmdCancelTask);
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse bodyUpdateAsynCommand = httpsUtil.doPostJsonGetStatusLine(urlUpdateAsynCommand, header, jsonRequest);
        
        System.out.println("UpdateAsynCommand, response content:");
        System.out.print(bodyUpdateAsynCommand.getStatusLine());
        System.err.println(bodyUpdateAsynCommand.getContent());
        System.out.println();
        
        
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
