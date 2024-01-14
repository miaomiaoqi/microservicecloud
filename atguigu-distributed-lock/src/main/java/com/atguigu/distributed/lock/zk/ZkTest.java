package com.atguigu.distributed.lock.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkTest {

    public static void main(String[] args) throws InterruptedException, KeeperException {
        ZooKeeper zooKeeper = null;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 30000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    Event.KeeperState state = watchedEvent.getState();
                    if (Event.KeeperState.SyncConnected.equals(state) && Event.EventType.None.equals(watchedEvent.getType())) {
                        System.out.println("获取到连接了" + watchedEvent);
                        countDownLatch.countDown();
                    } else if (Event.KeeperState.Closed.equals(state)) {
                        System.out.println("关闭连接");
                    } else {
                        System.out.println("节点事件");
                    }
                }
            });
            countDownLatch.await();
            // 节点新增: 永久, 临时, 永久序列化, 临时序列化
            // zooKeeper.create("/atguigu/test1", "hello zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            // zooKeeper.create("/atguigu/test2", "hello zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            // zooKeeper.create("/atguigu/test3", "hello zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
            // zooKeeper.create("/atguigu/test4", "hello zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            // zooKeeper.create("/atguigu/test4", "hello zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            // zooKeeper.create("/atguigu/test4", "hello zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            // 查询 判断节点是否存在
            Stat stat = zooKeeper.exists("/atguigu", false);
            if (stat != null) {
                System.out.println("当前节点存在");
            } else {
                System.out.println("当前节点不存在");
            }

            // 获取当前节点中的数据内容
            byte[] data = zooKeeper.getData("/atguigu", false, stat);
            System.out.println("当前节点的内容: " + new String(data));

            // 获取当前节点的子节点
            List<String> children = zooKeeper.getChildren("/atguigu", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("节点的子节点发生变化");
                }
            });
            System.out.println("当前节点的子节点: " + children);

            // 更新: 版本号必须和当前节点的版本号一致, 否则更新失败, 也可以指定为 -1, 代表不关心版本号
            zooKeeper.setData("/atguigu", "wawa...".getBytes(), stat.getVersion());

            // 删除
            // zooKeeper.delete("/atguigu/test1", -1);

            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        }
    }

}
