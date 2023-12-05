package com.miaoqi.bigdata.zkdist;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

public class DistributeClient {

    private static final String CONNECT_STRING = "127.0.0.1:2220,127.0.0.1:2221,127.0.0.1:2222";
    private static final int SESSION_TIMEOUT = 2000;
    public static final String PARENT_NODE = "/servers";
    private ZooKeeper zkClient;
    // 获取线程和业务线程是分开的
    private volatile List<String> serverList;

    public void getConnect() throws Exception {
        zkClient = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 收到事件通知后的回调函数(应该是我们自己的事件处理逻辑)
                // 重复监听
                try {
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getServerList() throws KeeperException, InterruptedException {
        // 获取子节点信息, 并对父节点监听
        List<String> children = zkClient.getChildren(PARENT_NODE, true);
        ArrayList<String> servers = new ArrayList<>();
        for (String child : children) {
            // child只是子节点的名字
            byte[] data = zkClient.getData(PARENT_NODE + "/" + child, false, null);
            servers.add(new String(data));
        }
        serverList = servers;
        System.out.println(serverList);
    }

    /**
     * 业务功能
     *
     * @author miaoqi
     * @date 2018/9/13
     * @param
     * @return
     */
    public void handleBussiness() throws InterruptedException {
        System.out.println("client start working...");
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        DistributeClient distributeClient = new DistributeClient();
        // 获取zk连接
        distributeClient.getConnect();
        // 获取servers子节点的信息, 从中获取服务器信息列表
        distributeClient.getServerList();
        // 开启业务
        distributeClient.handleBussiness();
    }

}
