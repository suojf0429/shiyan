package com.suo.pagetest;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 86131
 */
@Slf4j
@Configuration
@SpringBootApplication
@MapperScan("com.suo.pagetest.mapper")
@ServletComponentScan   //扫描@WebFilter注解，把过滤器创建出来
@EnableTransactionManagement  //开启事务注解支持
public class PageTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(PageTestApplication.class, args);
    }

}
