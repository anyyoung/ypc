package application.zookeeper;

import application.config.ServerConfig;
import application.protocol.ProtocolSelector;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zooKeeper 的服务端
 * @version v1.0
 * @author
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
            zooKeeper = new ZooKeeper(address, ZkConst.ZK_SESSION_TIMEOUT,  event -> {
                            if (event.getState() == Watcher.Event.KeeperState.SyncConnected){
                                 countDownLatch.countDown();
                            }
                        });
            countDownLatch.await();
            System.out.println("connected to zookeeper success ！connected to ： "+address);
        }catch (Exception e){
            LOGGER.error("connected to zookeeper failed ： "+e.getMessage() , e);
        }
        return zooKeeper;
    }

    public String createNode(ZooKeeper zK,  byte[] bytes , String servicePath ,CreateMode createMode) {
        String path = null;
        try {
            path = zK.create(servicePath, bytes , ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
            LOGGER.info("create zookeeper node ({} => {})",path , bytes.toString());
        }catch (Exception e) {
            LOGGER.error("", e);
        }
        return path;
    }

    public Stat exist(ZooKeeper zooKeeper, String path){
        Stat stat = null;
        try {
             stat = zooKeeper.exists(path,
                     event -> LOGGER.info(event.toString()));
        } catch (Exception e) {
            LOGGER.error("zookeeper exists failed : ", e);
        }
        return stat;
    }

    public List<String> getChildren(ZooKeeper zooKeeper, String path){
        List<String> children = null;
        try {
            children =zooKeeper.getChildren(path, event -> {
                LOGGER.info(event.toString());
            });
        } catch (Exception e) {
            LOGGER.info("zookeeper getChildren failed ： ", e);
        }
        return children;
    }

    public byte[] getData(ZooKeeper zooKeeper , String path){
        byte[] bytes = null;
        try {
            bytes = zooKeeper.getData(path, true, null);
        } catch (Exception e) {
            LOGGER.error("zookeeper getData failed : ",e);
        }
        return bytes;
    }

}
