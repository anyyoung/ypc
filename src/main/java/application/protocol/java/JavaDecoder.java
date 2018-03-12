package application.protocol.java;

import application.protocol.Protocol;

/**
 * say some thing
 *
 * @version v1.0
 * @author
 * @date 3/9/2018
 */
public class JavaDecoder implements Protocol {
    @Override
    public <T> T deSerialize(byte[] bytes, Class<T> clazz){
        return null;
    }

    @Override
    public <T> byte[] serialize(T obj) {
        return new byte[0];
    }

}
