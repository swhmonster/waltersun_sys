package com.waltersun.lastesttech.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author walter
 * @date 2021-06-03 15:02
 */
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {
    @Value("${async.executor.thread.core_pool_size}")
    private int corePoolSize;
    @Value("${async.executor.thread.max_pool_size}")
    private int maxPoolSize;
    @Value("${async.executor.thread.queue_capacity}")
    private int queueCapacity;
    @Value("${async.executor.thread.name.prefix}")
    private String namePrefix;

    /**
     * resource注入使用
     *
     * @return excutor
     */
    @Bean(name = "asyncServiceExecutor")
    public Executor asyncServiceExecutor() {
        log.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 配置队列大小
        executor.setQueueCapacity(queueCapacity);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(namePrefix);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * resource注入使用
     * 获取forkjoin公共池
     *
     * @return excutor
     */
    @Bean(name = "forkJoinPool")
    public ForkJoinPool forkJoinPool() {
        log.info("get ForkJoinPool.commonPool()");
        return ForkJoinPool.commonPool();
    }
}
