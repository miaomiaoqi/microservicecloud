package com.miaoqi.springboot.springbootquartz.scheduler;

import com.miaoqi.springboot.springbootquartz.job.CronJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 实现ApplicationRunner可以在项目启动时就执行run方法进行任务的注册
 *
 * @author miaoqi
 * @date 2019/1/15
 */
@Component
public class CronScheduler implements ApplicationRunner {

    /**
     * 注入任务调度器
     */
    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 任务名称
        String name = "CronScheduler";
        // 任务所属分组
        String group = "TestQuartz";
        // 删除现有任务, 防止重复添加导致项目启动失败
        JobKey jobKey = JobKey.jobKey(name, group);
        scheduler.deleteJob(jobKey);
        // 构建corn表达式触发器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/15 * * * * ?");
        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(CronJob.class).withIdentity(name, group).build();
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
        // 将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
