package application.server;

import application.config.ServerConfig;
import application.exception.YpcClientConnectionException;
import application.parameter.Invocation;
import application.server.callback.Callback;
import application.server.callback.Result;
import application.server.callback.ResultCallback;
import application.zookeeper.address.ServerURI;
import io.netty.channel.ChannelFuture;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * say some thing
 *
 * @version v1.0
 * @author
 * @date
 */
@Service
@EnableConfigurationProperties
public abstract class AbstractClient {
    private Logger logger = LoggerFactory.getLogger(AbstractClient.class);

    private ConcurrentHashMap<String, ChannelFutureManager> channels = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Callback> callbacks = new ConcurrentHashMap<>();

    private String concateAddress(ServerURI uri){
        if (uri!=null){
            return uri.getHost()+":"+uri.getPort();
        }
        return  null;
    }

    public Callback sync(Invocation invocation){
        String address = concateAddress(invocation.getServerURI());
        ChannelFutureManager channelFutureManager = null;
        if (channels.get(address)==null){
            channelFutureManager = connect(address);
            if (channelFutureManager==null || !channelFutureManager.isAvailable()){
                throw  new YpcClientConnectionException("can not conected to : "+address);
            }
        }
        ChannelFuture channelFuture = channelFutureManager.getChannelFuture();
        Callback callback = sendMessage(channelFuture, invocation);
        return callback;
    }

    public Callback sendMessage(ChannelFuture channelFuture, Invocation invocation){
        Callback callback = initCallback(invocation);
        channelFuture.channel().writeAndFlush(invocation);
        return  callback;
    }

    public Callback initCallback(Invocation invocation){
        String uuid = UUID.randomUUID().toString();
        Callback callback = new ResultCallback(invocation.getServerURI().getTimeout() , invocation.getClassName()+":"+invocation.getMethodName());
        callbacks.putIfAbsent(uuid, callback);
        return  callback;
    }

    public void putCallback(Result result){
        if (result!=null){
            Callback callback = callbacks.get(result.getSerilNo());
            if (callback !=null){
                callback.putResult(result);
            }else {
                throw new YpcClientConnectionException("find no request Callback...");
            }
        }
    }

    public abstract ChannelFutureManager connect(String address);

}
