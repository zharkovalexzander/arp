package ctos.iot.messaging.senders;

import ctos.iot.messaging.templates.StructuredMessage;
import ctos.iot.messaging.templates.SystemMessage;
import ctos.iot.threading.Emulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

public class JmsMessageSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsMessageSender.class);
    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void sendMessage(final StructuredMessage messages) {
        System.out.println(messages.toString());
        this.jmsTemplate.send(queue, s -> s.createObjectMessage(messages));
    }
}
