package application.zookeeper;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * say some thing
 *
 * @version v1.0
 * @author
 * @date 3/9/2018
 */
public class ZkConst {
    public static final String SUCCESS_CODE = "R000";
    public static final String ERROR_CODE = "R1000";
    //zk_session的超时时间默认为5000
    public static int ZK_SESSION_TIMEOUT = 5000;
    public static final String DATA_NODE = "/node_";
}
