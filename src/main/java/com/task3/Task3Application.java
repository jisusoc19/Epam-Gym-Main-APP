package com.task3;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@SpringBootApplication
@EnableFeignClients
public class Task3Application {


	public static void main(String[] args) {
		SpringApplication.run(Task3Application.class, args);
	}

    @PostConstruct
    public void init() {
    	log.info("main ha sido inicializado");
    }


}
