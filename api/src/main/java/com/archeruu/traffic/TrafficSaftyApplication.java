package com.archeruu.traffic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.archeruu.traffic", "com.archeruu.core"})
public class TrafficSaftyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrafficSaftyApplication.class, args);
    }

}
