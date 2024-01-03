package com.example.app1.config;

import com.example.app2.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp2 {

    @Bean(name = "userService")
    public UserService userServiceFromapp2(){
        return new UserService();
    }


}
