package ctos.iot.threading;

import ctos.iot.structure.LightSensorModule;
import ctos.iot.structure.LightSensorRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LightSensorsEmulator implements Runnable {
    private static final Long MAX = 40L;

    @Autowired
    private LightSensorRouter lightSensorRouter;

    @Autowired
    private LightSensorModule lightSensorModule;

    private GPSEmulator gpsEmulator;

    public void setGpsEmulator(GPSEmulator gpsEmulator) {
        this.gpsEmulator = gpsEmulator;
    }

    public void init() {
        lightSensorRouter.connectModule(lightSensorModule);
    }

    @Override
    public void run() {
        while (true) {
            if (gpsEmulator.isStop()) {
                try {
                    lightSensorModule.enter();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
