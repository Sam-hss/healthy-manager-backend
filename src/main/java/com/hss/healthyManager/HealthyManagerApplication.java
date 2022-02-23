package com.hss.healthyManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.hss.healthyManager.dao")
@SpringBootApplication
public class HealthyManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthyManagerApplication.class, args);
    }

}
