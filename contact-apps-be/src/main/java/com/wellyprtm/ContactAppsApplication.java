package com.wellyprtm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication( exclude = {
        SecurityAutoConfiguration.class
})
@EntityScan(basePackages = { "com.wellyprtm" })
@ComponentScan({ "com.wellyprtm" })
public class ContactAppsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContactAppsApplication.class, args);
    }
}
