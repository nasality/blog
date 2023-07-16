package com.bytedance.blog.question;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableSwagger2Doc
@EnableDiscoveryClient  //开启服务发现
@EnableFeignClients
public class QuestionApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class);
    }

}
