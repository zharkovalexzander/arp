package ctos.iot.threading;

import ctos.iot.structure.GPSModule;
import ctos.iot.structure.LightSensorRouter;
import ctos.iot.structure.MessagingModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class AsynchronousService {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MessagingModule messagingModule;

    @Autowired
    private GPSModule gpsModule;

    @Autowired
    private LightSensorRouter lightSensorRouter;

    public void executeAsynchronously() {
        GPSEmulator gpsEmulator = applicationContext.getBean(GPSEmulator.class);
        gpsEmulator.setUp();
        LightSensorsEmulator lightSensorsEmulator = applicationContext.getBean(LightSensorsEmulator.class);
        lightSensorsEmulator.init();
        lightSensorsEmulator.setGpsEmulator(gpsEmulator);
        Emulator emulator = applicationContext.getBean(Emulator.class);
        emulator.setModule(gpsModule);
        emulator.setModule(messagingModule);
        emulator.setModule(lightSensorRouter);
        taskExecutor.execute(lightSensorsEmulator);
        taskExecutor.execute(gpsEmulator);
        taskExecutor.execute(emulator);
    }
}
