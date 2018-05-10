package ctos.iot.structure;

import ctos.iot.Pair;
import ctos.iot.messaging.templates.SystemMessage;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class GPSModule extends GenericModule {
    private Queue<Pair<Double, Double>> coordiantes;

    public GPSModule() {
        super();
    }

    @Override
    public void sendPingMessage(int port, ModuleRouter to) {
        super.sendPingMessage(port, to);
        SystemMessage<GPSModuleMessage> message = new SystemMessage<>(true);
        message.setDeviceType(moduleName());
        message.setDeviceId(this.id);
        message.setData(new GPSModuleMessage());
        to.receiveMessage(port, message);
    }

    @Override
    public String moduleName() {
        return "gps";
    }

    public void sendData() {
        if (coordiantes == null) {
            coordiantes = new ConcurrentLinkedQueue<>();
        }
        Pair<Double, Double> pair = coordiantes.poll();
        if (pair != null) {
            SystemMessage<GPSModuleMessage> message = new SystemMessage<>(false);
            message.setDeviceType("'" + moduleName() + "'");
            message.setDeviceId("'" + this.id + "'");
            message.setData(new GPSModuleMessage().putCoords(pair.getKey(), pair.getValue()));
            this.receiver.receiveMessage(port, message);
        }
    }

    public void putCoord(Pair<Double,Double> current) {
        if (coordiantes == null) {
            coordiantes = new ConcurrentLinkedQueue<>();
        }
        this.coordiantes.offer(Pair.of(current.getKey(), current.getValue()));
    }

    class GPSModuleMessage implements Serializable {
        private Map<String, Pair> coords;

        public GPSModuleMessage() {
            coords = new HashMap<>();
            coords.put("coords", Pair.of((double) 0, (double) 0));
        }

        public Pair getCoords() {
            return coords.get("coords");
        }

        public GPSModuleMessage putCoords(Double lat, Double lon) {
            this.coords.put("coords", Pair.of(lat, lon));
            return this;
        }

        @Override
        public String toString() {
            String stringBuilder = "'" +
                    coords.get("coords").getKey().toString() +
                    "," + coords.get("coords").getValue().toString() +
                    "'";
            return stringBuilder;
        }
    }
}
