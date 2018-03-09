package application.zookeeper.pojo;

/**
 * say some thing
 *
 * @version v1.0
 * @author 17120050
 * @date 3/9/2018
 */
public class ZkService {

    private String serviceClass;
    private String timeout;

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "ZkService{" +
                "serviceClass='" + serviceClass + '\'' +
                ", timeout='" + timeout + '\'' +
                '}';
    }
}
