package ctos.iot.messaging.templates;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedList;

public class StructuredMessage implements Serializable {
    private LinkedList<SystemMessage> messages;

    public StructuredMessage() {
        messages = new LinkedList<>();
    }

    public StructuredMessage(LinkedList<SystemMessage> messages) {
        this.messages = messages;
    }

    public void addMessage(SystemMessage systemMessage) {
        this.messages.add(systemMessage);
    }

    public LinkedList<SystemMessage> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return messages.toString();
    }
}
