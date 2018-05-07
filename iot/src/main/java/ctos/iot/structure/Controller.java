package ctos.iot.structure;

import ctos.iot.messaging.templates.StructuredMessage;
import ctos.iot.messaging.templates.SystemMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class Controller {
    private Map<Integer, Module> modules;
    private Map<Integer, SystemMessage> messages;

    public Controller() {
        modules = new HashMap<>();
        messages = new HashMap<>();
    }

    public void connectModule(Module module) {
        int initialPort = 2;
        while (!portIsAvailable(initialPort)) {
            initialPort *= 2;
        }

        modules.put(initialPort, module);
        messages.put(initialPort, module.sendPingMessage());
    }

    public void sendData() {
        MessagingModule module = null;
        for (Integer i : this.modules.keySet()) {
            if (this.modules.get(i).sendPingMessage().getDeviceType().equals(MessagingModule.class.getName())) {
                module = (MessagingModule) this.modules.get(i);
                break;
            }
        }
        if (module != null) {
            LinkedList<SystemMessage> messages = new LinkedList<>();
            for (Integer i : this.messages.keySet()) {
                messages.add(this.messages.get(i));
            }
            module.sendMessage(new StructuredMessage(messages));
        }
    }

    private boolean portIsAvailable(int port) {
        return !modules.containsKey(port);
    }
}
