package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonServiceConfig {

    @Bean
    public RestTemplate restTemplate()
}
