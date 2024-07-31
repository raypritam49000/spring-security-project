package com.security.rest.api;

import com.security.rest.api.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Arrays;

@SpringBootApplication
public class SpringSecurityProjectApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityProjectApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringSecurityProjectApplication.class);
    }
}
