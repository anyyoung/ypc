package application.rebalance;
import static application.rebalance.Rebalance.*;
public enum RebalanceEnum {
    //目前仅引入了MurMurHash的负载均衡算法
    MUR_MUR_HASH_REBALANCE("MurMurHash");

    private Rebalance rebalance;

    RebalanceEnum(String enumName){
        switch (enumName){
            case MUR_MUR_HASH:
                rebalance = new MurMurHashRebalance();
                break;
                default:
                    rebalance = new MurMurHashRebalance();
        }
    }

    public Rebalance getRebalance() {
        return rebalance;
    }
}
