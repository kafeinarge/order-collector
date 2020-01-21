package com.turkcell.crm.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.turkcell.crm.ordermanagement.repository")
@SpringBootApplication
public class OrderCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCollectorApplication.class, args);
    }

}
