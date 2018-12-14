package com.futrue.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: QuartzApplication
 *  @package: com.futrue.schedule
 *  @Date: Created in 2018/11/13 下午3:02
 *  @email yuhan.tang@magicwindow.cn
 *  @Description: quartz
 */
@ComponentScan("com.futrue.schedule")
@EntityScan(value = "com.futrue.common.*")
@EnableAutoConfiguration(exclude={
        JpaRepositoriesAutoConfiguration.class //禁止springboot自动加载持久化bean
})
@SpringBootApplication
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class);
    }
}
