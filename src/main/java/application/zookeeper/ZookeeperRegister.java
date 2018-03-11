package application.zookeeper;

import application.config.ProtocolConfig;
import application.config.ServerConfig;
import application.protocol.Protocol;
import application.protocol.ProtocolSelector;
import application.server.pojo.YpcServerConf;
import application.zookeeper.address.ServerURI;
import application.zookeeper.pojo.ZkServerConf;
import application.zookeeper.pojo.ZkServiceConf;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;
@Service
@Scope("singleton")
public class ZookeeperRegister implements InitializingBean{
    private static  Logger logger = LoggerFactory.getLogger(ZookeeperRegister.class);
    @Autowired
    private ProtocolConfig protocolConfig;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private ZookeeperServer zookeeperServer;

    @Autowired
    public ProtocolSelector protocolSelector;
    private  Protocol protocol;
    private ZkServerConf zkServerConf;
    private YpcServerConf ypcServerConf;
    private String appNode;
    private int appPort;

    private void initApp(ZooKeeper zooKeeper) {
        logger.info("-------------注册app -------------");
        try{
            String path = zookeeperServer.createNode(zooKeeper, null, appNode , CreateMode.PERSISTENT);
        }catch (Exception e){
            logger.error("create appNode failed: ",e);
        }
    }

    public void init(ZooKeeper zooKeeper , List<ZkServiceConf> zkServiceConfs){
        logger.info("注册的服务为： "+zkServiceConfs);
        try {
            protocol = protocolSelector.getDecoder();
            ServerURI uri ;
            String host= Inet4Address.getLocalHost().getHostAddress();
            for (ZkServiceConf zkServiceConf : zkServiceConfs){
                uri = new ServerURI(host, appPort, Long.valueOf(zkServiceConf.getTimeout()));
                String servicePath = zkServiceConf.getServiceClass();
                byte[] data = protocol.serialize(uri);
                if (servicePath!=null){
                    zookeeperServer.createNode(zooKeeper, data  ,appNode+concatPath(servicePath) , CreateMode.EPHEMERAL_SEQUENTIAL);
                }
            }
            Thread.sleep(3000000l);
        } catch (Exception e){
            logger.error("regiter service failed: ",e);
        }


    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("----------开始进行zookeeper服务注册！----------");
        //ypc服务的配置初始化
        ypcServerConf = serverConfig.getYpcServerConf();
        if (ypcServerConf != null){
            //app节点
            appNode = concatPath(ypcServerConf.getAppName());
            //app端口
            appPort = Integer.valueOf(ypcServerConf.getAppPort());
        }

        //zkServer的配置初始化
        List<ZkServiceConf> zkServiceConfs = serverConfig.getServiceRegist();
        zkServerConf = serverConfig.getZkServerConf();
        String zkAddress = zkServerConf == null? null:zkServerConf.getZkAddress();
        ZooKeeper zooKeeper = zookeeperServer.connectServer(zkAddress);
        //注册节点
        initApp(zooKeeper);
        //注册服务
        init(zooKeeper, zkServiceConfs);

     }

     public String concatPath(String path){
        StringBuilder newPath;
        if (path!=null && path!="") {
            newPath = new StringBuilder("/").append(path);
            return newPath.toString();
        }
        return path;
     }



}
