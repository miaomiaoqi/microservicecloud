package com.atguigu.distributed.lock.service;

import com.atguigu.distributed.lock.lock.DistributedLockClient;
import com.atguigu.distributed.lock.lock.DistributedRedisLock;
import com.atguigu.distributed.lock.mapper.StockMapper;
import com.atguigu.distributed.lock.pojo.Stock;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * jvm 本地锁: 三种情况导致锁失效
 * 多例模式: 加锁的不是同一个对象了
 * 事务: AB两个线程, A更新数据库后还没 commit, 但是释放了锁, 此时 B 线程是会拿到旧数据, 导致锁失效
 * 集群部署: 本地锁失效s
 *
 * @author miaoqi
 * @date 2024-01-10 0:23:58
 */
@Service
// @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DistributedLockClient distributedLockClient;

    public void deduct() {

        DistributedRedisLock redisLock = this.distributedLockClient.getRedisLock("lock");
        redisLock.lock();
        try {
            // 1. 查询库存信息
            String stock = this.redisTemplate.opsForValue().get("stock");
            // 2. 判断库存是否充足
            if (stock != null && stock.length() != 0) {
                Integer st = Integer.valueOf(stock);
                if (st > 0) {
                    // 3. 扣减库存
                    this.redisTemplate.opsForValue().set("stock", String.valueOf(--st));
                }
            }
            this.test();
        } finally {
            redisLock.unlock();
        }
    }

    public void test() {
        DistributedRedisLock lock = this.distributedLockClient.getRedisLock("lock");
        lock.lock();
        System.out.println("测试可重入锁");
        lock.unlock();
    }

    /**
     *
     *
     * @author miaoqi
     * @date 2024-01-11 17:47:1
     */
    public void deduct6() {
        String uuid = UUID.randomUUID().toString();
        // 加锁 setnx
        while (!this.redisTemplate.opsForValue().setIfAbsent("lock", uuid, 3, TimeUnit.SECONDS)) {
            // 循环重试
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 重试: 递归调用
        try {
            // 1. 查询库存信息
            String stock = this.redisTemplate.opsForValue().get("stock");
            // 2. 判断库存是否充足
            if (stock != null && stock.length() != 0) {
                Integer st = Integer.valueOf(stock);
                if (st > 0) {
                    // 3. 扣减库存
                    this.redisTemplate.opsForValue().set("stock", String.valueOf(--st));
                }
            }
        } finally {
            // 先判断是否自己的锁, 再解锁
            String script =
                    "if redis.call('get', KEYS[1]) == ARGV[1] " + "then " + "return redis.call('del', KEYS[1]) " + "else " + "return 0 " + "end";
            this.redisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Arrays.asList("lock"), uuid);
            // if (StringUtils.equals(this.redisTemplate.opsForValue().get("lock"), uuid)) {
            //     this.redisTemplate.delete("lock");
            // }
        }
    }

    /**
     * mysql 乐观所
     *
     * @author miaoqi
     * @date 2024-01-11 13:16:32
     */
    public void deduct4() {
        List<Stock> stocks = stockMapper.selectList(new QueryWrapper<Stock>().eq("product_code", 1001));
        Stock stock = stocks.get(0);
        if (stock != null && stock.getCount() > 0) {
            stock.setCount(stock.getCount() - 1);
            Integer version = stock.getVersion();
            stock.setVersion(version + 1);
            if (stockMapper.update(stock, new UpdateWrapper<Stock>().eq("id", stock.getId()).eq("version", version)) == 0) {
                // 如果更新失败, 递归重试
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                deduct();
            }
        }
    }

    /**
     * 通过数据库悲观锁实现扣库存, 但是性能会非常低, 因为 for update 会锁定行记录
     *
     * @author miaoqi
     * @date 2024-01-11 12:28:31
     */
    @Transactional
    public void deduct3() {
        // 1. 查询库存信息并锁定库存信息
        List<Stock> stocks = stockMapper.queryStock("1001");
        // 这里取第一个库存
        Stock stock = stocks.get(0);

        // 2. 判断库存是否充足
        if (stock != null && stock.getCount() > 0) {
            // 3. 扣减库存
            stock.setCount(stock.getCount() - 1);
            this.stockMapper.updateById(stock);
        }
    }

    // @Transactional
    public synchronized void deduct2() {
        try {
            Stock stock = this.stockMapper.selectOne(new QueryWrapper<Stock>().eq("product_code", 1001));
            if (stock != null && stock.getCount() > 0) {
                stock.setCount(stock.getCount() - 1);
                System.out.println("余数: " + stock.getCount());
                this.stockMapper.updateById(stock);
            }
        } finally {
        }
    }

}
