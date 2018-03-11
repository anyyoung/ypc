package application.zookeeper;

import application.config.ServerConfig;
import application.protocol.ProtocolSelector;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * zooKeeper 的服务端
 * @version v1.0
 * @author 17120050
 * @date 3/9/2018
 */
@Service
@Scope("singleton")
@EnableAutoConfiguration
public class ZookeeperServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperServer.class);

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZooKeeper connectServer(String address){
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(address, ZkConst.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected){
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            System.out.println("连接zookeeper服务器成功！连接到："+address);
        }catch (IOException e){

        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }

    public String createNode(ZooKeeper zK,  byte[] bytes , String servicePath ,CreateMode createMode) {
        String path = null;
        try {
            path = zK.create(servicePath, bytes , ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
            LOGGER.debug("create zookeeper node ({} => {})",path , bytes.toString());
        }catch (Exception e) {
            LOGGER.error("", e);
        }
        return path;
    }

    /**
     * zk节点的路径为服务的class地址凭借官方的data路径
     * @param path
     * @return
     */
    public String createPath(String path){
        if (path != null && "".equals(path)){
            return ZkConst.ZK_DATA_PATH + path;
        }
        return  null;
    }
}
