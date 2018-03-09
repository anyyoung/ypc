package application.zookeeper;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * say some thing
 *
 * @version v1.0
 * @author 17120050
 * @date 3/9/2018
 */
public class ZkConst {

    int ZK_SESSION_TIMEOUT = 5000;
    String ZK_REGISTRY_PATH = "/ypc";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/service";
}
