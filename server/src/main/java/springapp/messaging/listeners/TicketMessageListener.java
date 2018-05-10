package springapp.messaging.listeners;

import ctos.iot.messaging.templates.StructuredMessage;
import ctos.iot.messaging.templates.SystemMessage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import springapp.entities.Pair;
import springapp.service.PointService;

import javax.jms.*;

public class TicketMessageListener implements MessageListener {
    private JmsTemplate jmsTemplate;
    private Queue queue;

    @Autowired
    private PointService pointService;

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
                manageMessage(structuredMessage);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }

    private void manageMessage(StructuredMessage structuredMessage) {
        String toJSON = structuredMessage.toString().replace('=', ':');
        JSONObject structuredMessageJSON = new JSONObject(toJSON);
        String devId = structuredMessageJSON.getString("id");
        pointService.addDevice(devId);
        JSONArray jsonArray = new JSONArray(structuredMessageJSON.getString("messages"));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jb = jsonArray.getJSONObject(i);
            pointService.addModule(devId, jb.getString("type"));
            switch (jb.getString("type")) {
                case "gps" :
                    String[] data = jb.getString("data").split(",");
                    pointService.add(devId, jb.getString("type"), Pair.of(Double.valueOf(data[0]), Double.valueOf(data[1])));
                    break;
                case "ls" :
                    pointService.add(devId, jb.getString("type"), Pair.of("pass", Long.valueOf(jb.getString("data"))));
                    break;
            }
        }
    }
}
