package com.miaoqi.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    /**
     * second(秒), minute（分）, hour（时）, day of month（日）, month（月）, day of week（周几）.
     * 0 * * * * MON-FRI
     *  【0 0/5 14,18 * * ?】 每天14点整，和18点整，每隔5分钟执行一次
     *  【0 15 10 ? * 1-6】 每个月的周一至周六10:15分执行一次
     *  【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
     *  【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
     *  【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次；
     */
    /*
     * second(秒 0-59), minute(分 0-59), hour(时 0-23), day of month(日 1-31), month(月 1-12), day of week(周几 0-7或SUN-SAT)
     * 0 * * * * MON-FRI
     */
    //    @Scheduled(cron = "0 * * * * MON-FRI")
    //    @Scheduled(cron = "0,1,2,3 * * * * MON-FRI") // , 代表枚举
    //    @Scheduled(cron = "0-3 * * * * MON-FRI") // - 代表区间
    @Scheduled(cron = "0/4 * * * * MON-FRI") // / 代表步长, 从0秒开始每4秒启动一次
    // ? 日和星期冲突匹配, 指定了一个的值, 另外一个就需要指定为?
    // L 最后
    // W 工作日
    // # 星期 4#2 第二个星期4
    public void hello() {
        System.out.println("hello...");
    }

}
