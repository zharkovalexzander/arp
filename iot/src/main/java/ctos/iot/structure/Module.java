package ctos.iot.structure;

import ctos.iot.messaging.templates.SystemMessage;

import java.io.Serializable;

public interface Module extends Serializable {
    void sendPingMessage(int port, ModuleRouter to);
    String moduleName();
    void sendData();
}
