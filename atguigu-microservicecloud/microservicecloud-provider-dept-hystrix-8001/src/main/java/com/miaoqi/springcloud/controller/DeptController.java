package com.miaoqi.springcloud.controller;

import com.miaoqi.springcloud.entities.Dept;
import com.miaoqi.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Autowired
    private DeptService service = null;

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    //一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    @HystrixCommand(
            // 线程池标识，仓壁模式的应用，每一个标识独立线程池，要保持标识唯⼀，不唯⼀的话就共⽤了
            threadPoolKey = "get",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            },
            // commandProperties 熔断的⼀些细节属性配置
            commandProperties = {
                // 服务降级，设置超时时间2秒
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "8000"),
                // 以下配置是熔断跳闸与自我修复：8秒钟内，请求次数达到2个，并且失败率在50%以上，就跳闸,跳闸后活动窗⼝设置为3s,即三秒后进行重试
                // 统计时间窗口定义
                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "8000"),
                // 最小请求数量
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "2"),
                // 统计时间框口内的异常百分比
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                // 自我修复活动窗口时长
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "3000")
                // 线程隔离策略, THREAD, SEMAPHORE
                // @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
                // 信号量大小
                // @HystrixProperty(name="execution.isolation.semaphore.maxConcurrentRequests", value="6")
            },
            // 异常返回方法，也是服务降级，方法的入参和返回值与该方法一致，在类上@DefaltProperties(defaultFallback="fallback")注解是为全类指定降级回调方法
            fallbackMethod = "processHystrix_Get"
    )
    public Dept get(@PathVariable("id") Long id) throws InterruptedException {
        if (id == 1) {
            Thread.sleep(5000L);
        }
        Dept dept = this.service.get(id);
        System.out.println("request: " + id);
        if (null == dept) {
            throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
        }
        return dept;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id) {
        Dept dept = new Dept();
        dept.setDeptno(id);
        dept.setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand");
        dept.setDb_source("no this database in MySQL");
        return dept;
    }
}
