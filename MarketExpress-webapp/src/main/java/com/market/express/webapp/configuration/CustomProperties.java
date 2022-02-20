package com.market.express.webapp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Configuration
@ConfigurationProperties(prefix = "com.market.express")
@Data
@Getter
@Setter
public class CustomProperties {

    private String apiUrl;

}