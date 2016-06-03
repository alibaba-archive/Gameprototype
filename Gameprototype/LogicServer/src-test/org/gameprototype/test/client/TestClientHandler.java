package org.gameprototype.test.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.gameprototype.proto.src.SocketModelDef;

/**
 * Created by haihong.xiahh on 2015/10/28.
 */
public class TestClientHandler extends SimpleChannelInboundHandler<SocketModelDef.SocketModelPB>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketModelDef.SocketModelPB msg) throws Exception {
        System.out.println("channel READ");
        System.out.println(msg.toString());
        System.out.println("getSerializedSize:" + msg.getSerializedSize());
    }

}
