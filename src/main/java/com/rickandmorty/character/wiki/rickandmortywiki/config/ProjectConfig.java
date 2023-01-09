package com.rickandmorty.character.wiki.rickandmortywiki.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableFeignClients(basePackages = "com.rickandmorty.character"
        + ".wiki.rickandmortywiki.service")
@EnableScheduling
public class ProjectConfig {
}
