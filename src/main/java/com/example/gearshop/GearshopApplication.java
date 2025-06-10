package com.example.gearshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GearshopApplication {

    public static void main(String[] args) {
        System.getProperties().put( "server.port", 8181 );  //8181 port is set here
        SpringApplication.run(GearshopApplication.class, args);
    }
}

    
    
