package ctos.iot.structure;

import ctos.iot.messaging.templates.StructuredMessage;
import ctos.iot.messaging.templates.SystemMessage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class ModuleRouter extends GenericModule {
    protected Map<Integer, Module> modules;
    protected Map<Integer, ConcurrentLinkedQueue<SystemMessage>> messages;

    public ModuleRouter() {
        super();
        modules = new HashMap<>();
        messages = new HashMap<>();
    }

    public boolean portIsAvailable(int port) {
        return !modules.containsKey(port);
    }

    public void connectModule(Module module) {
        int initialPort = 2;
        while (!portIsAvailable(initialPort)) {
            initialPort *= 2;
        }

        modules.put(initialPort, module);
        messages.put(initialPort, new ConcurrentLinkedQueue<>());

        module.sendPingMessage(initialPort, this);
    }

    public void receiveMessage(int port, SystemMessage message) {
        if (messages.get(port) != null) {
            messages.get(port).offer(message);
        }
        sendData();
    }

    public void sendData() {
        MessagingModule module = null;
        for (Integer i : this.modules.keySet()) {
            if (this.modules.get(i).moduleName().equals("msg")) {
                module = (MessagingModule) this.modules.get(i);
                break;
            }
        }
        if (module != null) {
            module.sendMessage(getMessages());
        }
    }

    public boolean messagesReady() {
        for (Integer i : this.messages.keySet()) {
            if (!this.modules.get(i).moduleName().equals("msg")
                    && this.messages.get(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public StructuredMessage getMessages() {
        LinkedList<SystemMessage> messages = new LinkedList<>();
        for (Integer i : this.messages.keySet()) {
            if (!this.modules.get(i).moduleName().equals("msg")) {
                SystemMessage systemMessage = this.messages.get(i).poll();
                if (systemMessage != null && !systemMessage.isTest()) {
                    messages.add(systemMessage);
                }
            }
        }
        return new StructuredMessage(id, messages);
    }
}
