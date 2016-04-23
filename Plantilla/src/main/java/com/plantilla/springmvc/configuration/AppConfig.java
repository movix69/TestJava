package com.plantilla.springmvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.plantilla.springmvc.repository","com.plantilla.springmvc.service","com.plantilla.springmvc.configuration","org.springframework.security.core.userdetails.UserDetailsService"})
public class AppConfig {

}
