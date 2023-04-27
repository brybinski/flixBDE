package com.bde.flix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlixApplication {

    public static Boolean DEBUG = true;
    public static void main(String[] args) {
        SpringApplication.run(FlixApplication.class, args);
    }

}
