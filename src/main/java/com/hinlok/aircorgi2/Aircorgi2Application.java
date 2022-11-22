package com.hinlok.aircorgi2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
public class Aircorgi2Application {

    public static void main(String[] args) {
        SpringApplication.run(Aircorgi2Application.class, args);
    }

}
