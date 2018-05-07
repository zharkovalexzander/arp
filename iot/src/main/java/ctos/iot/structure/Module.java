package ctos.iot.structure;

import ctos.iot.messaging.templates.SystemMessage;

import java.io.Serializable;

public interface Module extends Serializable {
    SystemMessage sendPingMessage();
}
