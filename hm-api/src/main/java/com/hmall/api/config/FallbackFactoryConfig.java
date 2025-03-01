package com.hmall.api.config;

import com.hmall.api.clients.fallback.ItemClientFallbackFactory;
import org.springframework.context.annotation.Bean;

public class FallbackFactoryConfig {

    @Bean
    public ItemClientFallbackFactory itemClientFallbackFactory() {
        return new ItemClientFallbackFactory();
    }
}
