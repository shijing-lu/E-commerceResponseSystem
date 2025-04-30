/*
 *
 *  * Copyright (C) ${YEAR}-${MONTH}-${DAY} 姚宇航
 *  * 江苏大学
 *
 */

package com.shijinglu.ecommerceresponsesystem;

import jakarta.annotation.PostConstruct;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.shijinglu.ecommerceresponsesystem.Dao")
@EnableScheduling
public class ECommerceResponseSystemApplication {

    @Autowired
    private Scheduler scheduler;

    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(ECommerceResponseSystemApplication.class, args);

    }

    @PostConstruct
    public void initScheduler() {
        try {
            if (scheduler != null && !scheduler.isStarted()) {
                scheduler.start(); // 启动调度器
                System.out.println("Quartz 调度器已手动启动");
            }
        } catch (SchedulerException e) {
            System.err.println("调度器启动失败: " + e.getMessage());
        }
    }
}
