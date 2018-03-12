package application.zookeeper.pojo;

/**
 * say some thing
 *
 * @version v1.0
 * @author
 * @date 3/9/2018
 */
public class ZkServerConf {

    private String zkAddress;

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    @Override
    public String toString() {
        return "ZkServerConf{" +
                "zkAddress='" + zkAddress + '\'' +
                '}';
    }
}
