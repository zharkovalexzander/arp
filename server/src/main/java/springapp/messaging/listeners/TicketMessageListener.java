package springapp.messaging.listeners;

import ctos.iot.messaging.templates.StructuredMessage;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

public class TicketMessageListener implements MessageListener {
    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String msg = ((TextMessage) message).getText();
                System.out.println(msg);
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        } else if (message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
                StructuredMessage structuredMessage = (StructuredMessage) objectMessage.getObject();
                System.out.println(structuredMessage.toString());
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }
}
