package raft.net;


import com.google.common.collect.Maps;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.commons.lang3.StringUtils;
import raft.enums.CommandType;
import raft.leaderstrategy.LeaderChooseAlgorithm;
import raft.model.RequestVote;
import raft.net.model.Packet;
import raft.processor.PacketProcessor;
import redis.Command;
import utils.CollectionUtils;

import java.net.ConnectException;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public class NettyClient {

    private Bootstrap b;


    private String candidateId;

    private ConcurrentMap<String, ChannelWrapper> addrToChannelMap = Maps.newConcurrentMap();

    private final ConcurrentMap<CommandType/* commandType */, PacketProcessor> processorTable =
            Maps.newConcurrentMap();

    public NettyClient(String candidateId) {
        this.candidateId = candidateId;
    }

    public void registerProcessor(CommandType commandType, PacketProcessor packetProcessor) {
        processorTable.put(commandType, packetProcessor);
    }

    public void start() {
        new Thread(() -> {

            try {
                try {
                    b = new Bootstrap(); // (1)

                    EventLoopGroup workerGroup = new NioEventLoopGroup();
                    b.group(workerGroup); // (2)
                    b.channel(NioSocketChannel.class); // (3)
                    b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
                    b.handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {

                            ObjectDecoder objectDecoder = new ObjectDecoder(1024 * 1024,
                                    ClassResolvers.weakCachingConcurrentResolver(this
                                            .getClass().getClassLoader()));

                            ch.pipeline().addLast(
                                    new ObjectEncoder(), objectDecoder,
                                    new NetClientHandler(processorTable));
                        }
                    });


                    // Wait until the connection is closed.
                    //  f.channel().closeFuture().sync();
                } finally {
                    //workerGroup.shutdownGracefully();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }


    /**
     * 创建连接
     *
     * @param addrs
     * @param localhost
     */
    public void createChannels(Set<String> addrs, String localhost) {

        if (CollectionUtils.isEmpty(addrs)) {
            return;
        }
        for (String addr : addrs) {

            if (StringUtils.isEmpty(addr)) {
                return;
            }
            // Start the client.
            String[] hostAndPort = addr.split(":");
            try {

                ChannelFuture f = b.connect(hostAndPort[0], Integer.parseInt(hostAndPort[1])).sync();

                addrToChannelMap.put(addr, new ChannelWrapper(f, addr));
            } catch (Exception e) {
                //如果是连接拒绝异常，则重试
                if (e instanceof ConnectException) {
                    new Thread(() -> {
                        boolean isTry = true;
                        while (isTry) {
                            try {
                                Thread.sleep(5000);

                                ChannelFuture f = b.connect(hostAndPort[0], Integer.parseInt(hostAndPort[1])).sync();

                                addrToChannelMap.put(addr, new ChannelWrapper(f, addr));
                                isTry = false;
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }).start();

                }

                e.printStackTrace();
            }
        }

    }


    public void close() {
        //workerGroup.shutdownGracefully();
    }

    public void startFindLeader(int term, String candidateId) {

        addrToChannelMap.entrySet().stream().forEach((entry) -> {

            ChannelFuture channelFuture = entry.getValue().getChannelFuture();

            Packet packet = new Packet();
            packet.setHeader(CommandType.LEADER_REQUEST);

            RequestVote requestVote = new RequestVote();
            requestVote.setTerm(term);
            requestVote.setCandidateId(candidateId);

            packet.setRequestVote(requestVote);

            channelFuture.channel().writeAndFlush(packet);
        });
    }
}