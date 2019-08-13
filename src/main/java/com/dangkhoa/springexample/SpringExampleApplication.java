package com.dangkhoa.springexample;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringExampleApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringExampleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("Spring boot application started!");
    }

}
