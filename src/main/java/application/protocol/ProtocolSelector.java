package application.protocol;

import application.exception.YpcConfigException;
import application.config.ProtocolConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * say some thing
 *
 * @author
 * @version v1.0
 * @date 3/9/2018
 */
@Service
@EnableAutoConfiguration
@Scope(scopeName = "singleton")
public class ProtocolSelector implements InitializingBean {

    @Autowired
    ProtocolConfig protocolConfig;

    private static Map<String, ProtocolEnum> protocolEnumMap = new HashMap<>();

    static {
        protocolEnumMap.put(Protocol.JAVA, ProtocolEnum.JAVA);
        protocolEnumMap.put(Protocol.PROTO_BUF, ProtocolEnum.PROTO_BUF);
    }

    /**
     * 获取序列化方式
     *
     * @return
     */
    public Protocol getDecoder() {
        String protocol = protocolConfig.getProtocol();
        //未配置用默认配置
        if (protocol == null || "null".equals(protocol) ) {
            return protocolEnumMap.get(Protocol.PROTO_BUF).getProtocol();
        }
        //已配置获取配置信息
        ProtocolEnum protocolEnum = protocolEnumMap.get(protocol);
        if (protocolEnum == null) {
            throw new YpcConfigException(
                    "The configured serialization protocol is not correct ！:" + protocol);
        }
        return protocolEnum.getProtocol();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("选择的："+getDecoder());
    }
}
