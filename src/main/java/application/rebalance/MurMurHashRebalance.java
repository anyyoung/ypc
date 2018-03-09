package application.rebalance;

import application.zookeeper.address.NodeAddress;
import application.zookeeper.address.ServerURI;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * MurMurHash基于MurMurHash的权重计算
 */
public class MurMurHashRebalance implements Rebalance{

    Random random =  new Random();

    @Override
    /**
     * 获取当前的权重计算规则
     */
    public String getRebalanceName() {
        return Rebalance.MUR_MUR_HASH;
    }

    @Override
    /**
     * 获取可以访问的clientURI
     * 根据当前访问的ip进行MurMurHash计算，根据权重选择相应的服务节点
     */
    public ServerURI getClientUri(NodeAddress[] nodeAddresses) {
        try {
            String host = Inet4Address.getLocalHost().getHostAddress();
            Long hash = MurMurHash.hash(host);
            hash = Math.abs(hash);
            if (nodeAddresses!=null && nodeAddresses.length>0){
                int index = hash == null ?
                        0 : (int)(hash % nodeAddresses.length);
                return nodeAddresses[index].getServerURI();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
