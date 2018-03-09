package application.config;

import application.server.pojo.YpcServer;
import application.zookeeper.pojo.ZkServer;
import application.zookeeper.pojo.ZkService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务配置文件加载类
 * @version v1.0
 * @author 17120050
 * @date
 */
@Component
@ConfigurationProperties(prefix = "rpc" , ignoreInvalidFields = true)
@PropertySource("classpath:/application.yml")
public class ServerConfig{
    //服务端参数
    private YpcServer ypcServer  = new YpcServer();
    //zkServer的参数
    private ZkServer zkServer = new ZkServer();
    //服务注册参数
    private List<ZkService> serviceRegist = new ArrayList<ZkService>();

    public YpcServer getYpcServer() {
        return ypcServer;
    }

    public void setYpcServer(YpcServer ypcServer) {
        this.ypcServer = ypcServer;
    }

    public List<ZkService> getServiceRegist() {
        return serviceRegist;
    }

    public void setServiceRegist(List<ZkService> serviceRegist) {
        this.serviceRegist = serviceRegist;
    }

    public ZkServer getZkServer() {
        return zkServer;
    }

    public void setZkServer(ZkServer zkServer) {
        this.zkServer = zkServer;
    }
}