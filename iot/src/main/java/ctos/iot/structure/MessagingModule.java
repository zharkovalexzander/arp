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
        this.type = this.getClass().getName();
    }

    @Override
    public SystemMessage sendPingMessage() {
        SystemMessage<LinkedList> message = new SystemMessage<>(true);
        message.setDeviceType(this.type);
        message.setDeviceId(this.ID);
        message.setData(new LinkedList());
        return message;
    }

    public void sendMessage(StructuredMessage structuredMessage) {
        jmsMessageSender.sendMessage(structuredMessage);
    }


}
