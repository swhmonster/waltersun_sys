package com.waltersun.lastesttech.scheduled;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author walter
 * @date 2021-10-26 9:45
 */
@Slf4j
@Configuration
@EnableScheduling
public class ScheduledTask {

    // 每5秒
    // @Scheduled(cron = "*/5 * * * * *")
    // 指定时间间隔5s
    // @Scheduled(fixedRate = 60000)
    // 每5分钟第0秒
    @Scheduled(cron = "0 */5 * * * *")
    public void executeTask() {
        LocalDateTime localDateTime = LocalDateTime.now();
        log.debug("ScheduledTask:{},second:{}", localDateTime, localDateTime.getSecond());
    }
}
