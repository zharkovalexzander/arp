package ctos.iot.structure;

import ctos.iot.messaging.templates.SystemMessage;

import java.io.Serializable;
import java.util.UUID;

public abstract class GenericModule implements Module, Serializable {
    protected String ID;
    protected String type;

    public GenericModule() {
        ID = UUID.randomUUID().toString();
    }

    @Override
    public abstract SystemMessage sendPingMessage();
}
