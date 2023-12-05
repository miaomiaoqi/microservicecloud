package com.miaoqi.springboot.springbootquartz.job;

import com.miaoqi.springboot.springbootquartz.service.ProductService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * cron表达式定时任务
 *
 * @author miaoqi
 * @date 2019/1/14
 */
@Component
public class CronJob extends QuartzJobBean {

    @Autowired
    private ProductService productService;

    /**
     * logback
     */
    static Logger logger = LoggerFactory.getLogger(CronJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("second cron job，execute at：{}", new Date());
        this.productService.business();
    }
}
