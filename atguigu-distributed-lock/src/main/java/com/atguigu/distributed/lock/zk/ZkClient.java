package com.atguigu.distributed.lock.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Component
public class ZkClient {

    private ZooKeeper zooKeeper;

    @PostConstruct
    public void init() {
        // 项目启动时获取连接
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 10000000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    Event.KeeperState state = watchedEvent.getState();
                    if (Event.KeeperState.SyncConnected.equals(state) && Event.EventType.None.equals(watchedEvent.getType())) {
                        System.out.println("获取到连接了" + watchedEvent);
                        countDownLatch.countDown();
                    } else if (Event.KeeperState.Closed.equals(state)) {
                        System.out.println("关闭连接");
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destory() {
        // 释放 zk 连接
        try {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ZkDistributedLock getLock(String lockName) {
        return new ZkDistributedLock(zooKeeper, lockName);
    }

}
