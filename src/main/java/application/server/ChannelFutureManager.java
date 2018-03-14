package application.server;

import io.netty.channel.ChannelFuture;

/**
 * say some thing
 *
 * @author
 * @version v1.0
 * @date 3/14/2018
 */
public class ChannelFutureManager {

    private ChannelFuture channelFuture;
    public ChannelFutureManager(ChannelFuture channelFuture){
        super();
        this.channelFuture = channelFuture;
    }

    public boolean isAvailable(){
        return channelFuture == null ? false:channelFuture.channel().isActive();
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }
}
