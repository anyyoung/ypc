package application.rebalance;

import application.zookeeper.address.NodeAddress;
import application.zookeeper.address.ServerURI;

import java.util.Random;

/**
 * 随机数权重计算
 * @version v1.0
 * @author
 * @date 3/9/2018
 */
public class RandomRebalance implements Rebalance{
    Random random = new Random();
    @Override
    public String getRebalanceName() {
        return Rebalance.RANDOM;
    }

    @Override
    public ServerURI getClientUri(NodeAddress[] nodeAddresses) {
        if (nodeAddresses!=null && nodeAddresses.length>0){
            int length = nodeAddresses.length;
            int weight = random.nextInt(length);
            return nodeAddresses[weight].getServerURI();
        }
        return null;
    }
}
