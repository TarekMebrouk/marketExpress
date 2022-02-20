package com.market.express.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.market.express.webapp.configuration.CustomProperties;

import lombok.Data;

@Data
@SpringBootApplication
public class MarketExpressWebappApplication implements CommandLineRunner {

    @Autowired
    private CustomProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(MarketExpressWebappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("WEB-APP {url : http://localhost:9001} \nREST-API {url : "+properties.getApiUrl()+"}");
    }

}
