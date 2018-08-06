package io.exhub.exhub_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author
 * @data 2018/8/6
 */
@Configuration
public class AsyncExecutor {

    /**
     * 初始化异步线程执行
     * @return
     */
    @Bean
    public Executor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}
