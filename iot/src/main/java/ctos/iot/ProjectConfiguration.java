package ctos.iot;

import ctos.iot.threading.AsynchronousService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProjectConfiguration implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(StartIOT.class);

    @Autowired
    private AsynchronousService asynchronousService;

    @Override
    public void run(String...args) {
        asynchronousService.executeAsynchronously();
    }
}