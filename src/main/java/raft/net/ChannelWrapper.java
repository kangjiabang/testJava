package raft.net;

import io.netty.channel.ChannelFuture;

public class ChannelWrapper {


    public ChannelWrapper(ChannelFuture channelFuture, String remoteAddr) {
        this.channelFuture = channelFuture;
        this.remoteAddr = remoteAddr;
    }

    private ChannelFuture channelFuture;

    private String remoteAddr;


    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
}
