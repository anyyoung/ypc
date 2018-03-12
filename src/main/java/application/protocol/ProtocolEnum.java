package application.protocol;

import application.protocol.java.JavaDecoder;
import application.protocol.protobuf.ProtoBufDecoder;

/**
 * say some thing
 *
 * @version v1.0
 * @author
 * @date 3/9/2018
 */
public enum ProtocolEnum {

    JAVA(Protocol.JAVA),
    PROTO_BUF(Protocol.PROTO_BUF);

    private Protocol protocol;

    ProtocolEnum(String protocolName){
        switch (protocolName) {
            case Protocol.JAVA:
                protocol = new JavaDecoder();
                break;
            case Protocol.PROTO_BUF:
                protocol = new ProtoBufDecoder();
                break;
            default:
                protocol = new ProtoBufDecoder();
        }
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
}
