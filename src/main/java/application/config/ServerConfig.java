package application.config;

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
@ConfigurationProperties(prefix = "rpc")
public class ServerConfig{

    private Map<String , Object> server  = new HashMap<String , Object>();
    private List<Map<String, Object>> serviceRegist = new ArrayList<Map<String, Object>>();


    public Map<String, Object> getServer() {
        return server;
    }

    public void setServer(Map<String, Object> server) {
        this.server = server;
    }

    public List<Map<String, Object>> getServiceRegist() {
        return serviceRegist;
    }

    public void setServiceRegist(List<Map<String, Object>> serviceRegist) {
        this.serviceRegist = serviceRegist;
    }
}