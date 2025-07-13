package com.laura.ms_ordenes.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String token = "Bearer TU_TOKEN_JWT_AQUI";
                template.header("Authorization", token);
            }
        };
    }
}
