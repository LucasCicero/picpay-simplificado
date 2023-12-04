package com.picpaysimplificado.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean//exporta classe para que outras consigam  carregar-la e fazer injeção de dependência
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
