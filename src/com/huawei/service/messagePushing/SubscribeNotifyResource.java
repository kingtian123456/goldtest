/*
 * File Name: com.huawei.m2m.nscl.web.rest.device.DeviceResource.java
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
package com.huawei.service.messagePushing;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huawei.utils.Constant;

public class SubscribeNotifyResource {

    @RequestMapping(value = Constant.DEVICE_ADDED_CALLBACK_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> recvAddDeviceNotify(
            @RequestBody Object addDevice_NotifyMessage) throws IOException {

        /*
         * parsing notifyMessage from JSON string:
         */

        //      Map<String, String> data1 = new HashMap<String, String>();
        //      data = JsonUtil.jsonString2SimpleObj(addDevice_NotifyMessage, data.getClass());
        //      String notifyType = data1.get("notifyType");
        //      String deviceId = data1.get("deviceId");
        //      String gatewayId = data1.get("gatewayId");
        //      String deviceInfo = data1.get("deviceInfo");
        //
        //      Map<String, String> data2 = new HashMap<String, String>();
        //      data2 = JsonUtil.jsonString2SimpleObj(deviceInfo, data2.getClass());
        //      String manufacturerId = data2.get("manufacturerId");
        //      String manufacturerName = data2.get("manufacturerName");
        //      String model = data2.get("model");
        //      String deviceType = data2.get("deviceType");
        //      ...
        
        
        /*
         * addDevice_NotifyMessage content:
         */
        //    {
        //        "notifyType": "deviceAdded",
        //        "deviceId": "4ad0510a-a985-496f-ba91-d6e2f72994d8",
        //        "gatewayId": "4ad0510a-a985-496f-ba91-d6e2f72994d8",
        //        "nodeType": "GATEWAY",
        //        "deviceInfo": {
        //        "nodeId": null,
        //            "name": null,
        //            "description": null,
        //            "manufacturerId": null,
        //            "manufacturerName": null,
        //            "mac": null,
        //            "location": null,
        //            "deviceType": null,
        //            "model": null,
        //            "swVersion": null,
        //            "fwVersion": null,
        //            "hwVersion": null,
        //            "protocolType": null,
        //            "bridgeId": null,
        //            "status": "OFFLINE",
        //            "statusDetail": "NOT_ACTIVE",
        //            "mute": null,
        //            "supportedSecurity": null,
        //            "isSecurity": null,
        //            "signalStrength": null,
        //            "sigVersion": null,
        //            "serialNumber": null,
        //            "batteryLevel": null
        //        }
        //    }


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = Constant.DEVICE_INFO_CHANGED_CALLBACK_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> recvModifyDeviceInfoNotify(
            @RequestBody Object modifyDeviceInfo_NotifyMessage) throws IOException {


        /*
         * modifyDevice_NotifyMessage content:
         */
        //    {
        //        "notifyType": "deviceInfoChanged",
        //        "deviceId": "4ad0510a-a985-496f-ba91-d6e2f72994d8",
        //        "gatewayId": "4ad0510a-a985-496f-ba91-d6e2f72994d8",
        //        "deviceInfo": {
        //            "nodeId": null,
        //            "name": null,
        //            "description": null,
        //            "manufacturerId": "010F",
        //            "manufacturerName": "Fibargroup",
        //            "mac": null,
        //            "location": null,
        //            "deviceType": "watermeter",
        //            "model": "0B00-1001",
        //            "swVersion": null,
        //            "fwVersion": null,
        //            "hwVersion": null,
        //            "protocolType": null,
        //            "bridgeId": null,
        //            "status": null,
        //            "statusDetail": null,
        //            "mute": null,
        //            "supportedSecurity": null,
        //            "isSecurity": null,
        //            "signalStrength": null,
        //            "sigVersion": null,
        //            "serialNumber": null,
        //            "batteryLevel": null
        //        }
        //    }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = Constant.DEVICE_DATA_CHANGED_CALLBACK_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> recvDeviceDataChangedNotify(
            @RequestBody Object deviceDataChanged_NotifyMessage) throws IOException {


        /*
         * deviceDataChanged_NotifyMessage content:
         */

//        {
//            "notifyType" : "deviceDataChanged",
//                "deviceId" : "********",
//                "gatewayId" : "*********",
//                "requestId" : "****",
//                "service" : {
//                    "serviceId" : "WaterMeter",
//                    "serviceType" : "WaterMeter",
//                    "data" : {
//                        "alarmType":"***",
//                        "swVersion":"***",
//                        "signalIntensity":"***",
//                        "eventTime":"***",
//                        "fwVersion":"***"
//                    }
//                "eventTime" : "****"
//                }
//        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}