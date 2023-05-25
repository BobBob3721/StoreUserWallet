package com.bob.storedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bob.storedemo.mapper")	//加载mapper的包路径
public class StoredemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoredemoApplication.class, args);
    }

}
