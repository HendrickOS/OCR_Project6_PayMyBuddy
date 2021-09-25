package com.example.PayMyBuddy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig extends com.example.PayMyBuddy.security.jwt.JwtConfig {

}
