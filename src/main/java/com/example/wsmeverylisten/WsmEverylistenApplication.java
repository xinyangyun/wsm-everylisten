package com.example.wsmeverylisten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan({"com", "com"})
public class WsmEverylistenApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsmEverylistenApplication.class, args);
    }



}
