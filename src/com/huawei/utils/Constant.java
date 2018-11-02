/*
 * File Name: com.huawei.utils.Constant.java
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
package com.huawei.utils;


public class Constant {
/*
    //please replace the IP and Port, when you use the demo.
    public static final String BASE_URL = "https://185.11.1.143:8743";

    //please replace the appId and secret, when you use the demo.
    public static final String APPID = "zLni5ehN2RAnUKxq9OoJ1Nj3df4a";
    public static final String SECRET = "b0pcUQYHNHxiaCZm8KQfCUuLo0Aa";
*/
	//please replace the IP and Port, when you use the demo.
	public static final String BASE_URL = "https://180.101.147.89:8743";
    //please replace the appId and secret, when you use the demo.
    public static final String APPID = "QdW4An8DOZfxdkve1fWUPkRjA64a";
    public static final String SECRET = "tBA33NmKcyjkZnkPSOcfASkWckca";

    /*
     *IP and port of callback url.
     *please replace the IP and Port of your Application deployment environment address, when you use the demo.
     */
    public static final String CALLBACK_BASE_URL = "https://103.45.106.53:8443";

    /*
     * complete callback url锛�
     * please replace uri, when you use the demo.
     */
    public static final String DEVICE_ADDED_CALLBACK_URL = CALLBACK_BASE_URL + "/goldtest/deviceAdded";
    public static final String DEVICE_INFO_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/goldtest/deviceInfoChanged";
    public static final String DEVICE_DATA_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/goldtest/deviceDataChanged";
    public static final String DEVICE_DELETED_CALLBACK_URL = CALLBACK_BASE_URL + "/goldtest/deviceDeleted";
    //public static final String MESSAGE_CONFIRM_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/commandConfirmData";
    public static final String SERVICE_INFO_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/updateServiceInfo";
    //public static final String COMMAND_RSP_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/commandRspData";
    //public static final String DEVICE_EVENT_CALLBACK_URL = CALLBACK_BASE_URL + "/deviceEvent";
    public static final String RULE_EVENT_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/RulEevent";
    public static final String DEVICE_DATAS_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/deviceDatasChanged";


    /*
     * Specifies the callback URL for the command execution result notification.
     * For details about the execution result notification definition.
     *
     * please replace uri, when you use the demo.
     */
    public static final String REPORT_CMD_EXEC_RESULT_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/reportCmdExecResult";


    //Paths of certificates.
    public static String SELFCERTPATH = "/src/resource/cert/outgoing.CertwithKey.pkcs12";
    public static String TRUSTCAPATH = "/src/resource/cert/ca.jks";
    
    public static String FWQPATH = "D:/apache-tomcat-8.0.36-windows-x64/apache-tomcat-8.0.36/webapps/goldtest/WEB-INF/classes/resource/cert/outgoing.CertwithKey.pkcs12";
    
    public static String TRFWQ = "D:/apache-tomcat-8.0.36-windows-x64/apache-tomcat-8.0.36/webapps/goldtest/WEB-INF/classes/resource/cert/ca.jks";
    //Password of certificates.
    public static String SELFCERTPWD = "IoM@1234";
    public static String TRUSTCAPWD = "Huawei@123";






    //*************************** The following constants do not need to be modified *********************************//

    /*
     * request header
     * 1. HEADER_APP_KEY
     * 2. HEADER_APP_AUTH
     */
    public static final String HEADER_APP_KEY = "app_key";
    public static final String HEADER_APP_AUTH = "Authorization";
    
    /*
     * Application Access Security:
     * 1. APP_AUTH
     * 2. REFRESH_TOKEN
     */
    public static final String APP_AUTH = BASE_URL + "/iocm/app/sec/v1.1.0/login";
    public static final String REFRESH_TOKEN = BASE_URL + "/iocm/app/sec/v1.1.0/refreshToken";
    

    /*
     * Device Management:
     * 1. REGISTER_DEVICE
     * 2. MODIFY_DEVICE_INFO
     * 3. QUERY_DEVICE_ACTIVATION_STATUS
     * 4. DELETE_DEVICE
     * 5. DISCOVER_INDIRECT_DEVICE
     * 6. REMOVE_INDIRECT_DEVICE
     */
    //注册直连设备的新接口地址
    //密码方式
    public static final String REGISTER_DEVICE = BASE_URL + "/iocm/app/reg/v1.1.0/deviceCredentials";
    //修改设备信息
    public static final String MODIFY_DEVICE_INFO = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    //激活状态
    public static final String QUERY_DEVICE_ACTIVATION_STATUS = BASE_URL + "/iocm/app/reg/v1.1.0/deviceCredentials";
    //删除设备
    public static final String DELETE_DEVICE = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    public static final String DISCOVER_INDIRECT_DEVICE = BASE_URL + "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
    public static final String REMOVE_INDIRECT_DEVICE = BASE_URL + "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";

    /*
     * Data Collection:
     * 1. QUERY_DEVICES
     * 2. QUERY_DEVICE_DATA
     * 3. QUERY_DEVICE_HISTORY_DATA
     * 4. QUERY_DEVICE_CAPABILITIES
     * 5. SUBSCRIBE_NOTIFYCATION
     */
    //批量查询设备的信息
    public static final String QUERY_DEVICES = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    //查询设备的单个信息
    public static final String QUERY_DEVICE_DATA = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    //查询设备历史数据
    public static final String QUERY_DEVICE_HISTORY_DATA = BASE_URL + "/iocm/app/data/v1.2.0/deviceDataHistory";
    //查询设备服务能力
    public static final String QUERY_DEVICE_CAPABILITIES = BASE_URL + "/iocm/app/data/v1.1.0/deviceCapabilities";
    //订阅接口
    public static final String SUBSCRIBE_NOTIFYCATION = BASE_URL + "/iocm/app/sub/v1.2.0/subscribe";
    
    
    /*
     * Signaling Delivery锛�
     * 1. POST_ASYN_CMD
     * 2. QUERY_DEVICE_CMD
     * 3. UPDATE_ASYN_COMMAND
     * 4. CREATE_DEVICECMD_CANCEL_TASK
     * 5. QUERY_DEVICECMD_CANCEL_TASK
     *
     */
    public static final String POST_ASYN_CMD = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    //查询设备命令
    public static final String QUERY_DEVICE_CMD = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String UPDATE_ASYN_COMMAND = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    //创建设备命令下发
    public static final String CREATE_DEVICECMD_CANCEL_TASK = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String QUERY_DEVICECMD_CANCEL_TASK = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";


    /*
     * notify Type
     * serviceInfoChanged|deviceInfoChanged|LocationChanged|deviceDataChanged|deviceDatasChanged
     * deviceAdded|deviceDeleted|messageConfirm|commandRsp|deviceEvent|ruleEvent
     */
    public static final String DEVICE_ADDED = "deviceAdded";//添加新设备
    public static final String DEVICE_INFO_CHANGED = "deviceInfoChanged";//设备信息变化
    public static final String DEVICE_DATA_CHANGED = "deviceDataChanged";//设备数据变化
    public static final String DEVICE_DELETED = "deviceDeleted";//删除设备
    public static final String MESSAGE_CONFIRM = "messageConfirm";//消息确认
    public static final String SERVICE_INFO_CHANGED = "serviceInfoChanged";//设备信息
    public static final String COMMAND_RSP = "commandRsp";//响应命令
    public static final String DEVICE_EVENT = "deviceEvent";//设备事件
    public static final String RULE_EVENT = "ruleEvent";//规则事件
    public static final String DEVICE_DATAS_CHANGED = "deviceDatasChanged";//设备数据批量变化
    
    public static final String deviceId = "deviceId";
    public static final String service = "service";
    public static final String serviceType = "serviceType";
    public static final String data = "data";
    public static final String onoff = "onoff";
    public static final String resultCode = "deviceId";
    public static final String deviceInfo = "deviceInfo";

}
