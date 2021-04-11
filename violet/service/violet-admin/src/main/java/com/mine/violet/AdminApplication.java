package com.mine.violet;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mine"})
@MapperScan("com.mine.violet.mapper")
@EnableTransactionManagement
@EnableDiscoveryClient //nacos注册
@EnableFeignClients // Feign
public class AdminApplication {
    public static void main(String[] args){
        SpringApplication.run(AdminApplication.class);
    }
}
