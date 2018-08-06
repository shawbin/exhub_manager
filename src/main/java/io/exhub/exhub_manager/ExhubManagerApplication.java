package io.exhub.exhub_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author
 * @date 2018/7/25
 */
@EnableAsync
@SpringBootApplication
@MapperScan("io.exhub.exhub_manager.mapper")
public class ExhubManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExhubManagerApplication.class, args);
    }
}
