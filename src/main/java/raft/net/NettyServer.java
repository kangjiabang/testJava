package raft.net;

import com.google.common.collect.Maps;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import raft.RaftClient;
import raft.enums.CommandType;
import raft.leaderstrategy.VoteComarator;
import raft.model.RequestVote;
import raft.model.RequestVoteReponse;
import raft.net.model.Packet;
import raft.processor.PacketProcessor;

import java.util.concurrent.ConcurrentMap;

/**
 * Discards any incoming data.
 */
public class NettyServer {

    private int port;

    private RaftClient raftClient;

    private final ConcurrentMap<CommandType/* commandType */, PacketProcessor> processorTable =
            Maps.newConcurrentMap();


    public NettyServer(int port, RaftClient raftClient) {
        this.port = port;
        this.raftClient = raftClient;
    }

    public void start() {
        new Thread(() -> {


            try {
                EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
                EventLoopGroup workerGroup = new NioEventLoopGroup();
                try {
                    ServerBootstrap b = new ServerBootstrap(); // (2)
                    b.group(bossGroup, workerGroup)
                            .channel(NioServerSocketChannel.class) // (3)
                            .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                                @Override
                                public void initChannel(SocketChannel ch) throws Exception {

                                    ObjectDecoder objectDecoder = new ObjectDecoder(1024 * 1024,
                                            ClassResolvers.weakCachingConcurrentResolver(this
                                                    .getClass().getClassLoader()));

                                    ch.pipeline().addLast(
                                            new ObjectEncoder(), objectDecoder,
                                            new NetServerHandler(processorTable));
                                }
                            })
                            .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                            .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

                    // Bind and start to accept incoming connections.
                    ChannelFuture f = b.bind(port).sync(); // (7)

                    // Wait until the server socket is closed.
                    // In this example, this does not happen, but you can do that to gracefully
                    // shut down your server.
                    f.channel().closeFuture().sync();
                } finally {
                    workerGroup.shutdownGracefully();
                    bossGroup.shutdownGracefully();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void registerProcessor(CommandType commandType, PacketProcessor packetProcessor) {
        processorTable.put(commandType, packetProcessor);
    }
}
