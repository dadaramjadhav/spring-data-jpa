package com.example;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.NoOpCacheManager;

import io.lettuce.core.api.StatefulConnection;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        try {
            connectionFactory.getConnection().ping(); // test if Redis is alive
            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofMinutes(60)) // Set TTL
                    .disableCachingNullValues()
                    .computePrefixWith(cacheName -> "myapp::" + cacheName + "::");

            return RedisCacheManager.builder(connectionFactory)
                    .cacheDefaults(config)
                    .build();
        } catch (Exception ex) {
            System.err.println("⚠️ Redis unavailable, falling back to NoOpCacheManager: " + ex.getMessage());
            return new NoOpCacheManager();
        }
    }

    // connection pooling
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // Use wildcard for generic pool config to satisfy method contract
        GenericObjectPoolConfig<StatefulConnection<?, ?>> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(10);
        poolConfig.setMinIdle(2);
        poolConfig.setMaxWait(Duration.ofSeconds(5));

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig)
                .commandTimeout(Duration.ofSeconds(1))
                .shutdownTimeout(Duration.ofMillis(1000)) // optional
                .build();

        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration("localhost", 6379);

        return new LettuceConnectionFactory(serverConfig, clientConfig);
    }

    @Bean
    public CacheErrorHandler cacheErrorHandler() {
        return new CacheErrorHandler() {

            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                System.out.println("Failed to GET from Redis cache '{}' for key '{}'. Falling back to DB. Error: {}");
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                System.out
                        .println("Failed to PUT in Redis cache '{}' for key '{}'. Proceeding without cache. Error: {}");
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                System.out.println("Failed to EVICT Redis cache '{}' for key '{}'. Ignoring. Error: {}");
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                System.out.println("Failed to CLEAR Redis cache '{}'. Ignoring. Error: {}");
            }
        };
    }

}
