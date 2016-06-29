package main.plantilla.springmvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"main.plantilla.springmvc.repository","main.plantilla.springmvc.service","main.plantilla.springmvc.configuration","org.springframework.security.core.userdetails.UserDetailsService"})
public class AppConfig {
}
