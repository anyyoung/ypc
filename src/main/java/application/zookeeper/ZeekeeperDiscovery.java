package application.zookeeper;

import application.config.ServerConfig;
import application.protocol.Protocol;
import application.protocol.ProtocolSelector;
import application.server.pojo.YpcServerConf;
import application.zookeeper.address.NodeAddress;
import application.zookeeper.address.ServerURI;
import application.zookeeper.pojo.ZkServerConf;
import application.zookeeper.pojo.ZkServiceConf;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * say some thing
 *
 * @version v1.0
 * @author
 * @date 3/12/2018
 */
@Service
@Scope("singleton")
public class ZeekeeperDiscovery {
    private static Logger LOGGER = LoggerFactory.getLogger(ZeekeeperDiscovery.class);

    public static Map<String, NodeAddress[]> servic_array = new ConcurrentHashMap<>();

    @Autowired
    ProtocolSelector protocolSelector;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private ZookeeperServer zookeeperServer;

    private Protocol protocol;
    private String appNode ;
    private int port ;

    public void disCovery(ZkServiceConf zkServiceConf  , ZooKeeper zookeeper){
        String className = null;
        if (zkServiceConf != null){
            className = zkServiceConf.getServiceClass();
        }
        String fartherPath = "/"+appNode+"/"+className;
        List<String> children = zookeeperServer.getChildren(zookeeper, fartherPath);
        int num = 0;
        NodeAddress[] nodeAddresses = null;
        if (children !=null && children.size()>0){
            nodeAddresses = new NodeAddress[children.size()];
            for (String path : children){
                byte[] bytes = zookeeperServer.getData(zookeeper, fartherPath+"/"+path);
                if (protocol == null){
                    protocol = protocolSelector.getDecoder();
                }
                ServerURI serverURI = protocol.deSerialize(bytes,ServerURI.class);
                nodeAddresses[num] = new NodeAddress(path , serverURI);
                num++;
            }
            servic_array.put(className , nodeAddresses);
        }
        LOGGER.info("======= 发现如下服务："+ Arrays.toString(nodeAddresses));
    }

    public void disCoveryService() throws Exception {
        LOGGER.info("------------------ start disCovery service : ----------------");
        //zkConf
        ZkServerConf zkServerConf = serverConfig.getZkServerConf();
        List<ZkServiceConf> zkServiceConfs = serverConfig.getServiceRegist();
        //zk
        String zkAddress = zkServerConf == null ? null:zkServerConf.getZkAddress();
        ZooKeeper zooKeeper = zookeeperServer.connectServer(zkAddress);
        //ypcServerConf
        YpcServerConf ypcServerConf = serverConfig.getYpcServerConf();
        if (ypcServerConf !=null){
            appNode = ypcServerConf.getAppName();
            port = Integer.valueOf(ypcServerConf.getAppPort());
        }
        //protocol
        protocol = protocolSelector.getDecoder();
        for (ZkServiceConf zkServiceConf : zkServiceConfs){
            disCovery(zkServiceConf , zooKeeper);
        }
    }
}
