package application.zookeeper.pojo;

/**
 * say some thing
 *
 * @version v1.0
 * @author 17120050
 * @date 3/9/2018
 */
public class ZkServer {

    private String zkAddress;

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    @Override
    public String toString() {
        return "ZkServer{" +
                "zkAddress='" + zkAddress + '\'' +
                '}';
    }
}
