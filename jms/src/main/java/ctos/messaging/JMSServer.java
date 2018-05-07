package ctos.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration(exclude={ DataSourceAutoConfiguration.class })
@ImportResource({ "classpath:EmbeddedActiveMQ.xml"})
public class JMSServer {
    public static void main(String[] args) {
        SpringApplication.run(JMSServer.class, args);
    }
}