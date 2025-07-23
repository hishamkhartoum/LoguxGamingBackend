package com.hadef.loguxgaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LoguxGamingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoguxGamingApplication.class, args);
    }

}
