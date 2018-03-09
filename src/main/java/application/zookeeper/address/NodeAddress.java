package application.zookeeper.address;

import java.io.Serializable;

/**
 * say some thing
 *
 * @version v1.0
 * @author 17120050
 * @date 3/9/2018
 */
public class NodeAddress implements Serializable{

    private String nodeName;
    private ServerURI serverURI;

    public NodeAddress(String nodeName, ServerURI serverURI){
        this.nodeName = nodeName;
        this.serverURI = serverURI;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public ServerURI getServerURI() {
        return serverURI;
    }

    public void setServerURI(ServerURI clientURI) {
        this.serverURI = serverURI;
    }
}
