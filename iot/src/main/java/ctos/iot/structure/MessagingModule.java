package ctos.iot.structure;

import ctos.iot.messaging.senders.JmsMessageSender;
import ctos.iot.messaging.templates.StructuredMessage;
import ctos.iot.messaging.templates.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class MessagingModule extends GenericModule {

    @Autowired
    private JmsMessageSender jmsMessageSender;

    public MessagingModule() {
        super();
    }

    @Override
    public void sendPingMessage(int port, ModuleRouter to) {
        super.sendPingMessage(port, to);
        SystemMessage<LinkedList> message = new SystemMessage<>(true);
        message.setDeviceType(moduleName());
        message.setDeviceId(this.id);
        message.setData(new LinkedList());
        to.receiveMessage(port, message);
    }

    @Override
    public String moduleName() {
        return "msg";
    }

    @Override
    public void sendData() { }

    public void sendMessage(StructuredMessage structuredMessage) {
        jmsMessageSender.sendMessage(structuredMessage);
    }


}
