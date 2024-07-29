package com.powernode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 系统管理模块启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ManagerServiceApplication {
    public static void main(String []args){
        SpringApplication.run(ManagerServiceApplication.class,args);
    }
}
