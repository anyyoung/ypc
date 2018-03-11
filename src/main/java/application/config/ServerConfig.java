package application.config;

import application.server.pojo.YpcServerConf;
import application.zookeeper.pojo.ZkServerConf;
import application.zookeeper.pojo.ZkServiceConf;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    private YpcServerConf ypcServerConf= new YpcServerConf();
    //zkServer的参数
    private ZkServerConf zkServerConf = new ZkServerConf();
    //服务注册参数
    private List<ZkServiceConf> serviceRegist = new ArrayList<ZkServiceConf>();

    public YpcServerConf getYpcServerConf() {
        return ypcServerConf;
    }

    public void setYpcServer(YpcServerConf ypcServerConf) {
        this.ypcServerConf = ypcServerConf;
    }

    public List<ZkServiceConf> getServiceRegist() {
        return serviceRegist;
    }

    public void setServiceRegist(List<ZkServiceConf> serviceRegist) {
        this.serviceRegist = serviceRegist;
    }

    public ZkServerConf getZkServerConf() {
        return zkServerConf;
    }

    public void setZkServer(ZkServerConf zkServerConf) {
        this.zkServerConf = zkServerConf;
    }
}