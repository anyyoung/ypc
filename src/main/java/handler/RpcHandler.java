package handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import pojo.RpcRequest;
import pojo.RpcResponse;

public class RpcHandler extends SimpleChannelInboundHandler<RpcRequest>{
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcHandler.class);
    
    private final Map<String, Object> handlerMap;
    
    public RpcHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }
    
    
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {
        RpcResponse response = new RpcResponse();
        response.setRequestId(request.getRequestId());
        try {
            Object result = handle(request);
            response.setResult(result);
        }catch (Throwable e) {
            response.setError(e);
        }
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
    
    private Object handle(RpcRequest request) throws Throwable{
        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);
        
        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();
        
        
        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
        return serviceFastMethod.invoke(serviceBean, parameters);
    }

}
