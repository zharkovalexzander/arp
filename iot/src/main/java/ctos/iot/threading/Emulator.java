package ctos.iot.threading;


import ctos.iot.structure.Controller;
import ctos.iot.structure.GPSModule;
import ctos.iot.structure.MessagingModule;
import ctos.iot.structure.Module;
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

    public void setModule(Module module) {
        this.controller.connectModule(module);
    }

    @Override
    public void run() {
        while (true);
    }
}
