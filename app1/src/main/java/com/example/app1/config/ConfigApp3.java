package com.example.app1.config;

import com.example.app3.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp3 {

    @Bean(name = "userService")
    public UserService userServiceFromapp3(){
        return new UserService();
    }


}
