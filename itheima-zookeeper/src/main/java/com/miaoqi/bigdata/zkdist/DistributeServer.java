package com.miaoqi.bigdata.zkdist;

import org.apache.zookeeper.*;

/**
 * 分布式服务端
 *
 * @author miaoqi
 * @date 2018/9/13
 */
public class DistributeServer {

    private static final String CONNECT_STRING = "127.0.0.1:2220,127.0.0.1:2221,127.0.0.1:2222";
    private static final int SESSION_TIMEOUT = 2000;
    public static final String PARENT_NODE = "/servers";
    private ZooKeeper zkClient;

    public void getConnect() throws Exception {
        zkClient = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, new Watcher() {
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

    /**
     * 向zk集群注册服务信息
     *
     * @author miaoqi
     * @date 2018/9/13
     * @param hostName
     * @return
     */
    public void registerServer(String hostName) throws KeeperException, InterruptedException {
        // 创建为临时节点, 因为服务端崩溃就可以自动销毁
        String create = zkClient.create(PARENT_NODE + "/server", hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostName + " is online...;;" + create);
    }

    /**
     * 业务功能
     *
     * @author miaoqi
     * @date 2018/9/13
     * @param
     * @return
     */
    public void handleBussiness(String hostName) throws InterruptedException {
        System.out.println(hostName + " start working...");
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        DistributeServer server = new DistributeServer();
        // 获取zk连接
        server.getConnect();
        // 利用zk连接注册服务器信息
        server.registerServer(args[0]);
        // 启动业务功能
        server.handleBussiness(args[0]);
    }

}
