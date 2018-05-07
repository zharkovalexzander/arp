package ctos.iot.messaging.templates;

import javax.jms.ObjectMessage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SystemMessage<T extends Serializable> implements Serializable {
    private String deviceId;
    private String deviceType;
    private T data;
    private boolean isTest;

    public SystemMessage(boolean isTest) {
        this.isTest = isTest;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        Map<String, Object> toPrint = new HashMap<>();
        toPrint.put("id", deviceId);
        toPrint.put("type", deviceType);
        toPrint.put("data", data.toString());
        return toPrint.toString();
    }
}
