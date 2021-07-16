package com.waltersun.lastesttech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author walter
 * @date 2021-07-16
 */
@MapperScan({"com.waltersun.lastesttech.mapper"})
@SpringBootApplication
public class WaltersunApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaltersunApplication.class, args);
    }

}
