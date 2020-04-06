package com.apeiron.igor.application;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GigaspaceConfig {

    @Bean
    public GigaSpace gigaSpace(@Value("${gigaspace.url}") String gigaspaceUrl) {
        UrlSpaceConfigurer urlSpaceConfigurer = new UrlSpaceConfigurer(gigaspaceUrl);
        return new GigaSpaceConfigurer(urlSpaceConfigurer).gigaSpace();
    }
}
