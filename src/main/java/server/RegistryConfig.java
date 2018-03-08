package server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rpc")
@PropertySource("classpath:/config.properties")  
public class RegistryConfig {
    private String registryAddress;
    
    private String serverAddress;
    
    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public String toString() {
        return "RegistryConfig [registryAddress=" + registryAddress + ", serverAddress=" + serverAddress + "]";
    }
    
}
