package ctos.iot.messaging.templates;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedList;

public class StructuredMessage implements Serializable {
    private String id;
    private LinkedList<SystemMessage> messages;

    public StructuredMessage() {
        messages = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StructuredMessage(String id, LinkedList<SystemMessage> messages) {
        this.id = id;
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
        return "{id:\"" + id + "\", messages:\"" + messages.toString() + "\"}";
    }
}
