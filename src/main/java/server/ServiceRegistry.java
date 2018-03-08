package server;

import java.util.concurrent.CountDownLatch;

import javax.print.DocFlavor.STRING;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import intf.Constant;

@Service
public class ServiceRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistry.class);
    private CountDownLatch latch = new CountDownLatch(1);
    
    @Autowired
    private RegistryConfig config;
    
    public void register(String data) {
        if (data != null) {
            ZooKeeper zk = connectServer();
            if (zk != null) {
                createNode(zk, data);
            }
        }
    }
    
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(config.getRegistryAddress(), Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (Exception  e) {
            LOGGER.error("Exception is : ",e);
        }
        return zk;
    }
    
    public void createNode(ZooKeeper zK, String data) {
        try {
            byte[] bytes = data.getBytes();
            String path = zK.create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            LOGGER.debug("create zookeeper node ({} => {})",path , data);
        }catch (Exception e) {
            LOGGER.error("", e);
        }
    }
}
