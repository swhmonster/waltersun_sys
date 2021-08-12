package com.waltersun.lastesttech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;

/**
 * @author walter
 * @date 2021-07-16
 */
@MapperScan({"com.waltersun.lastesttech.mapper"})
@EnableMethodCache(basePackages = "com.waltersun.lastesttech.service")
@EnableCreateCacheAnnotation
//@EnableEurekaClient
@SpringBootApplication
public class WaltersunApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaltersunApplication.class, args);
    }

}
