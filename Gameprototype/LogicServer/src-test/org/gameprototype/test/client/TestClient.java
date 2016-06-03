package org.gameprototype.test.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import org.gameprototype.logic.login.protocol.LoginProtocol;
import org.gameprototype.proto.src.LoginDTODef;
import org.gameprototype.proto.src.SocketModelDef;
import org.gameprototype.protocol.Protocol;

/**
 * Created by haihong.xiahh on 2015/10/28.
 */
public class TestClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast("encoder", new ProtobufEncoder());
                    ch.pipeline().addLast("decoder", new ProtobufDecoder(SocketModelDef.SocketModelPB.getDefaultInstance()));
                    ch.pipeline().addLast("handler", new TestClientHandler());
                };
            });

            Channel ch = bootstrap.connect("127.0.0.1", 9090).sync().channel();

            // login

            SocketModelDef.SocketModelPB.Builder builder = SocketModelDef.SocketModelPB.newBuilder();
            builder.setArea(0);
            builder.setCommand(LoginProtocol.LOGIN_CREQ);
            builder.setType(Protocol.TYPE_LOGIN);
            LoginDTODef.LoginDTOPB.Builder loginBuilder = LoginDTODef.LoginDTOPB.newBuilder();
            loginBuilder.setAccount("xiahaihong");
            loginBuilder.setPassword("xiahaihong");
            builder.setLoginMsg(loginBuilder.build());
            ch.writeAndFlush(builder.build());
            for (;;) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
