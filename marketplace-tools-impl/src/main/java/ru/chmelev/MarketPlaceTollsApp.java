package ru.chmelev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "ru.chmelev.controllers.feign")
public class MarketPlaceTollsApp {
    public static void main(String[] args) {
        SpringApplication.run(MarketPlaceTollsApp.class,args);


    }
}
