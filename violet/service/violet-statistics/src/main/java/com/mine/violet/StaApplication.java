package com.mine.violet;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mine"})
@MapperScan("com.mine.violet.mapper")
@EnableDiscoveryClient //nacos注册
@EnableFeignClients // Feign
@EnableScheduling //开启定时任务
public class StaApplication {
    public static void main(String[] args){
        SpringApplication.run(StaApplication.class);
    }
}
