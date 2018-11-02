package com.huawei.testMessagePush;

import java.util.ArrayList;
import java.util.List;

public class NotifyType {

    //please replace the IP and Port to your localhost IP and port, when you use the testMessagePush.
    public static final String TEST_CALLBACK_BASE_URL = "http://192.168.11.246:8888/";

    public static List<String> notifyTypes = new ArrayList<>();
    public static List<String> getNotifyTypes () {
        notifyTypes.add(SERVICE_INFO_CHANGED);
        notifyTypes.add(DEVICE_INFO_CHANGED);
        notifyTypes.add(DEVICE_DATA_CHANGED);
        notifyTypes.add(DEVICE_ADDED);
        notifyTypes.add(DEVICE_DELETED);
        notifyTypes.add(MESSAGE_CONFIRM);
        notifyTypes.add(COMMAND_RSP);
        notifyTypes.add(DEVICE_EVENT);
        notifyTypes.add(RULE_EVENT);
        notifyTypes.add(DEVICE_DATAS_CHANGED);
        return notifyTypes;
    }

    /*
     * notify Type
     * serviceInfoChanged|deviceInfoChanged|LocationChanged|deviceDataChanged|deviceDatasChanged
     * deviceAdded|deviceDeleted|messageConfirm|commandRsp|deviceEvent|ruleEvent
     */
    public static final String DEVICE_ADDED = "deviceAdded";
    public static final String DEVICE_INFO_CHANGED = "deviceInfoChanged";
    public static final String DEVICE_DATA_CHANGED = "deviceDataChanged";
    public static final String DEVICE_DELETED = "deviceDeleted";
    public static final String MESSAGE_CONFIRM = "messageConfirm";
    public static final String SERVICE_INFO_CHANGED = "serviceInfoChanged";
    public static final String COMMAND_RSP = "commandRsp";
    public static final String DEVICE_EVENT = "deviceEvent";
    public static final String RULE_EVENT = "ruleEvent";
    public static final String DEVICE_DATAS_CHANGED = "deviceDatasChanged";
}
