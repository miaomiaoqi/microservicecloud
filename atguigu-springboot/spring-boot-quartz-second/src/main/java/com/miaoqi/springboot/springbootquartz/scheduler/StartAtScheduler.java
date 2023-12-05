package com.miaoqi.springboot.springbootquartz.scheduler;

import com.miaoqi.springboot.springbootquartz.job.StartAtJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 实现ApplicationRunner可以在项目启动时就执行run方法进行任务的注册
 *
 * @author miaoqi
 * @date 2019/1/15
 */
@Component
public class StartAtScheduler implements ApplicationRunner {

    /**
     * 注入任务调度器
     */
    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 设置开始时间为1分钟后
        long startAtTime = System.currentTimeMillis() + 1000 * 60;
        // 任务名称
        String name = "StartAtScheduler";
        // 任务所属分组
        String group = "TestQuartz";
        JobKey jobKey = JobKey.jobKey(name, group);
        scheduler.deleteJob(jobKey);
        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(StartAtJob.class).withIdentity(name, group).build();
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTime)).build();
        // 将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
