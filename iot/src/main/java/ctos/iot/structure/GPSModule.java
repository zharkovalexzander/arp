package ctos.iot.structure;

import ctos.iot.Pair;
import ctos.iot.messaging.templates.SystemMessage;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class GPSModule extends GenericModule {

    public GPSModule() {
        super();
        this.type = this.getClass().getName();
    }

    @Override
    public SystemMessage sendPingMessage() {
        SystemMessage<GPSModuleMessage> message = new SystemMessage<>(true);
        message.setDeviceType(this.type);
        message.setDeviceId(this.ID);
        message.setData(new GPSModuleMessage());
        return message;
    }

    class GPSModuleMessage implements Serializable {
        private Map<String, List<Pair>> coords;

        public GPSModuleMessage() {
            coords = new HashMap<>();
            coords.put("coords", new LinkedList<>());
        }

        public List<Pair> getCoords() {
            return coords.get("coords");
        }

        public void putCoords(Double lat, Double lon) {
            this.coords.get("coords").add(Pair.of(lat, lon));
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Pair pair : coords.get("coords")) {
                stringBuilder.append("{ ")
                        .append(pair.getKey().toString())
                        .append(", ").append(pair.getValue().toString())
                        .append(" }").append(System.lineSeparator());
            }
            return stringBuilder.toString();
        }
    }
}
