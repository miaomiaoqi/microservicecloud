package com.miaoqi.bigdata.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SimpleZkClient {

    private static final String connectString = "127.0.0.1:2220,127.0.0.1:2221,127.0.0.1:2222";
    private static final int sessionTimeout = 2000;

    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 收到事件通知后的回调函数(应该是我们自己的事件处理逻辑)
                System.out.println(event.getType() + "---" + event.getPath());
                // 重复监听
                try {
                    zkClient.getChildren("/", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 测试创建节点
    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        // 数据的增删改查
        // 参数1: 节点路径, 参数2: 节点数据, 参数3: 节点权限, 参数4: 节点类型
        String nodeCreated = zkClient.create("/idea", "hellozk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
    }

    // 测试获取节点
    @Test
    public void testGetChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

    // 判断节点是否存在
    @Test
    public void testExist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/idea", false);
        System.out.println(stat == null);
    }

    // 获取znode的数据
    @Test
    public void getDate() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/idea", false, null);
        System.out.println(new String(data));
    }

    // 删除znode数据
    @Test
    public void deleteZnode() throws KeeperException, InterruptedException {
        // 参数2: 指定要删除的版本, -1表示都删除
        zkClient.delete("/idea", -1);
    }

    // 更新znode数据
    @Test
    public void setZnode() throws KeeperException, InterruptedException {
        // 参数2: 指定要删除的版本, -1表示都删除
        zkClient.setData("/app1", "i miss you".getBytes(), -1);
    }
}
