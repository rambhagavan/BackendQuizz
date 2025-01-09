package com.quizzapp.demo.config;

import org.springframework.context.annotation.Bean;

public class RedisConfig {
	@Bean
    public RedisCommands<String, String> redisCommands() {
        // Connect to Redis (default: localhost:6379)
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        return connection.sync();
    }
}
