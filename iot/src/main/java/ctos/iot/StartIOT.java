package ctos.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@ImportResource({ "classpath:app_config.xml" })
public class StartIOT {
    public static void main(String[] args) {
        SpringApplication.run(StartIOT.class, args);
    }
}
