package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import util.SerializationUtil;
public class RpcEncoder extends MessageToByteEncoder<Object>{
    private Class<?> generiClass;
    
    public RpcEncoder( Class<?> generiClass) {
        this.generiClass = generiClass;
    }
    
    @Override
    protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (generiClass.isInstance(in)) {
            byte[] data = SerializationUtil.serialize(in);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
