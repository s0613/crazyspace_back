package org.crazyspace_edu.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CrazyspaceEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrazyspaceEduApplication.class, args);
    }

}
