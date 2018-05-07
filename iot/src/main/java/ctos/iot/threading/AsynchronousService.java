package ctos.iot.threading;

import ctos.iot.structure.GPSModule;
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

    public void executeAsynchronously() {
        Emulator emulator = applicationContext.getBean(Emulator.class);
        emulator.setGpsModule(new GPSModule());
        emulator.setMessagingModule(messagingModule);
        taskExecutor.execute(emulator);
    }
}
