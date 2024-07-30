package com.powernode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 系统管理模块启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching//开启注解式缓存。默认为redis
public class ManagerServiceApplication {
    public static void main(String []args){
        SpringApplication.run(ManagerServiceApplication.class,args);
    }
}
