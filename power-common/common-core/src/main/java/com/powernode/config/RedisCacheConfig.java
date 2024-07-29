package com.powernode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * Redis缓存配置类
 */
@Configuration
public class RedisCacheConfig {
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        //创建redis缓存配置类
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        //进行redis的配置
        redisCacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
                .entryTtl(Duration.ofDays(7))//统一设置redis值中默认过期时间
                .disableCachingNullValues();//禁止使用redis空值
        return redisCacheConfiguration;
    }
}
