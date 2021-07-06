package com.example.mybatiscommonmapperspringbootdemo;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.mybatiscommonmapperspringbootdemo.mybatis.mapper")
@EnableScheduling
@Slf4j
public class MybatisCommonMapperSpringBootDemoApplication implements ApplicationRunner {
    @Autowired
    Scheduler scheduler;

    public static void main(String[] args) {
        SpringApplication.run(MybatisCommonMapperSpringBootDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger-1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(20).repeatForever())
                .build();

        try {
            scheduler.start();
            if (!scheduler.checkExists(trigger.getKey())) {
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static class SimpleJob implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            log.info("hello world");
        }
    }

}
