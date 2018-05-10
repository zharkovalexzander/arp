package ctos.iot.structure;

import ctos.iot.messaging.templates.SystemMessage;
import org.springframework.stereotype.Component;

@Component
public class LightSensorModule extends GenericModule {
    public LightSensorModule() {
        super();
    }

    @Override
    public String moduleName() {
        return "lsm";
    }

    @Override
    public void sendData() { }

    public void sendData(boolean b) {
        SystemMessage<Boolean> message = new SystemMessage<>(false);
        message.setDeviceType("'" + moduleName() + "'");
        message.setDeviceId("'" + this.id + "'");
        message.setData(b);
        this.receiver.receiveMessage(port, message);
    }

    public void enter() {
        sendData(true);
    }

    public void exit() {
        sendData(false);
    }
}
