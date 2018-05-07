package ctos.iot.messaging.senders;

import ctos.iot.messaging.templates.StructuredMessage;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

public class JmsMessageSender {
    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void sendMessage(final StructuredMessage messages) {
        this.jmsTemplate.send(queue, s -> s.createObjectMessage(messages));
    }
}
