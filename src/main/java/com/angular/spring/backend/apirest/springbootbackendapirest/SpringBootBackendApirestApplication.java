package com.angular.spring.backend.apirest.springbootbackendapirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SpringBootBackendApirestApplication  {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootBackendApirestApplication.class, args);
    }


}
