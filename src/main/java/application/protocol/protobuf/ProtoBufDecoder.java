package application.protocol.protobuf;

import application.protocol.Protocol;

/**
 * say some thing
 *
 * @version v1.0
 * @author 17120050
 * @date 3/9/2018
 */
public class ProtoBufDecoder implements Protocol {
    @Override
    public <T> byte[] serialize(T obj) {
        return new byte[0];
    }

    @Override
    public <T> T deSerialize(byte[] bytes, Class<?> clazz) {
        return null;
    }
}
