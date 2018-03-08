package server;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.socket.nio.NioServerBoss;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import annotation.RpcService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
@Service
public class RpcServer implements ApplicationContextAware, InitializingBean{
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServer.class);
    
   @Autowired
   private RegistryConfig config;
   @Autowired
   private ServiceRegistry serviceRegistry;
   //存放接口与服务对象之间的映射关系
   private Map<String, Object> handleMap = new HashMap<>();
   

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        //找到所有注解的方法
        Map<String , Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class);
        //如果服务不为空
        if (!CollectionUtils.isEmpty(serviceBeanMap)) {
            for(Object serviceBean : serviceBeanMap.values()) {
                String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
                handleMap.put(interfaceName, serviceBean);
            }
        }
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
       EventLoopGroup bossGroup = new NioEventLoopGroup();
       EventLoopGroup workerGroup = new NioEventLoopGroup();
       try {
           ServerBootstrap bootstrap = new ServerBootstrap();
           bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
               .childHandler(new ChannelInitializer<SocketChannel>() {
                   @Override
                   public void initChannel(SocketChannel channel) throws Exception{
                       
                       
                       
                   }
                   
            });
       } catch (Exception e) {
           
    }
    }

}
