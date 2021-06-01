package com.waltersun.lastesttech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.waltersun.lastesttech.mapper"})
@SpringBootApplication
public class WaltersunApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaltersunApplication.class, args);
    }

}
