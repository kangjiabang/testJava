/*
 * Copyright 2014 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package raft.net;

import com.google.common.collect.Maps;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import raft.enums.CommandType;
import raft.leaderstrategy.VoteComarator;
import raft.model.RequestVote;
import raft.model.RequestVoteReponse;
import raft.net.model.Packet;
import raft.processor.PacketProcessor;
import raft.processor.VoteRequestProcessor;
import raft.processor.VoteResponseProcessor;

import java.util.concurrent.ConcurrentMap;

public class NetServerHandler extends SimpleChannelInboundHandler<Object> {


    private ConcurrentMap<CommandType/* commandType */,PacketProcessor> processorTable =
            Maps.newConcurrentMap();

    protected NetServerHandler(ConcurrentMap<CommandType/* commandType */,PacketProcessor> processorTable) {
        this.processorTable = processorTable;
        System.out.println("ConfigServerHandler()");

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        /*channelManager.addChannel(ctx.channel());
        channelManager.processChannel();*/
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush("HELLO: Client is connected.\n");

    }



    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        Thread.sleep(1000);

        Packet packet = (Packet) msg;

        if (packet.getHeader() == CommandType.HEATBEAT) {
            System.out.println("recevie leader heartbeat");
        } if (packet.getHeader() == CommandType.LEADER_REQUEST) {
            System.out.println("recevie requestVote");

            VoteRequestProcessor voteRequestProcessor = (VoteRequestProcessor)processorTable.get(packet.getHeader());
            RequestVoteReponse requestVoteReponse = (RequestVoteReponse)voteRequestProcessor.processRequest(ctx,packet);

            Packet packet1 = new Packet();
            packet1.setHeader(CommandType.LEADER_RESPONSE);
            packet1.setRequestVoteReponse(requestVoteReponse);

            ctx.writeAndFlush(requestVoteReponse);

        }
        System.out.println("message from client:" + msg.toString());

    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();

        if (ctx.channel().isActive()) {
            ctx.writeAndFlush("ERR: " +
                    cause.getClass().getSimpleName() + ": " +
                    cause.getMessage() + '\n').addListener(ChannelFutureListener.CLOSE);
        }
    }
}

