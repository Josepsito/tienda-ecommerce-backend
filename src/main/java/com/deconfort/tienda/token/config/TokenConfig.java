package com.deconfort.tienda.token.config;


import com.deconfort.tienda.token.properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(JwtProperties.class)
public class TokenConfig {
}
