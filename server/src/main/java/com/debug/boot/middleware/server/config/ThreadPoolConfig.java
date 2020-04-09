package com.debug.boot.middleware.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池的配置
 */
@Configuration
public class ThreadPoolConfig {

    //Todo: java config的方式显示注入Bean
    /**
     * 任务-线程池-调度配置
     * @return
     */
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(5);
        //最大核心线程数
        executor.setMaxPoolSize(10);
        //设定队列等待的被调度的任务的数量
        executor.setQueueCapacity(5);
        return executor;
    }
}
