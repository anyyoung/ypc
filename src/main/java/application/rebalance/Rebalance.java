package application.rebalance;

import application.zookeeper.address.NodeAddress;
import application.zookeeper.address.ServerURI;

public interface Rebalance {

    public static final String MUR_MUR_HASH = "MurMurHash";
    public static final String RANDOM = "random";

    public String getRebalanceName();
    public ServerURI getClientUri(NodeAddress[] nodeAddresses);
}
