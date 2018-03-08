package handler;

import java.util.List;

import org.springframework.util.SerializationUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import util.SerializationUtil;

public class RpcDecoder extends ByteArrayDecoder {
    
    private Class<?> genericClass;
    
    public RpcDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }
    
    public void decode(ChannelHandlerContext ctx, ByteBuf in , List<Object> out ) throws Exception{
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        
        Object obj = SerializationUtil.deserialize(data , genericClass);
        out.add(obj);
    }
    
}
