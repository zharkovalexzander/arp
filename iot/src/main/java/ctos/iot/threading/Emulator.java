package ctos.iot.threading;


import ctos.iot.structure.Controller;
import ctos.iot.structure.GPSModule;
import ctos.iot.structure.MessagingModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Emulator implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Emulator.class);

    @Autowired
    private Controller controller;

    public void setMessagingModule(MessagingModule messagingModule) {
        this.controller.connectModule(messagingModule);
    }

    public void setGpsModule(GPSModule gpsModule) {
        this.controller.connectModule(gpsModule);
    }

    @Override
    public void run() {
        while (true) {
            try {
                controller.sendData();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
