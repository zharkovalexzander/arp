package ctos.iot.structure;

import ctos.iot.messaging.templates.SystemMessage;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class LightSensorRouter extends ModuleRouter {
    private AtomicLong passengers;

    public LightSensorRouter() {
        super();
        passengers = new AtomicLong(0);
    }

    @Override
    public void connectModule(Module module) {
        if (module instanceof LightSensorModule) {
            super.connectModule(module);
        }

    }

    @Override
    public String moduleName() {
        return "ls";
    }

    @Override
    public void sendData() {
        SystemMessage<String> message = new SystemMessage<>(false);
        message.setDeviceType("'" + moduleName() + "'");
        message.setDeviceId("'" + this.id + "'");
        message.setData("'" + passengers.get() + "'");
        this.receiver.receiveMessage(port, message);
    }

    @Override
    public void receiveMessage(int port, SystemMessage message) {
        if (messages.get(port) != null) {
            Boolean enters = (Boolean) message.getData();
            if (enters != null && enters) {
                passengers.incrementAndGet();
            } else if (enters != null) {
                passengers.decrementAndGet();
            }
            sendData();
        }
    }
}
