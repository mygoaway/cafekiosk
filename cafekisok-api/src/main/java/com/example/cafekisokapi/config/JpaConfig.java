package com.example.cafekisokapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.storage.domain")
@EntityScan(basePackages = "com.example.storage.domain")
public class JpaConfig {

}