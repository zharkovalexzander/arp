package ctos.iot.structure;

import java.io.Serializable;
import java.util.UUID;

public abstract class GenericModule implements Module, Serializable {
    protected String id;
    transient protected ModuleRouter receiver;
    transient protected int port;

    public GenericModule() {
        id = UUID.randomUUID().toString();
        receiver = null;
    }

    @Override
    public void sendPingMessage(int port, ModuleRouter to) {
        if (this.receiver == null) {
            this.receiver = to;
        }
        if (this.port == 0) {
            this.port = port;
        }
    }

    @Override
    public abstract String moduleName();

    @Override
    public abstract void sendData();
}
