package org.gameprototype.nettyhandler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.gameprototype.proto.src.SocketModelDef;

/**
 * Created by zhoubo on 15-9-1.
 */
public abstract  class HexDumpProxyPipelineFactory extends

        ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
        pipeline.addLast("protobufDecoder", new ProtobufDecoder(SocketModelDef.SocketModelPB.getDefaultInstance()));
        pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("protobufEncoder", new ProtobufEncoder());
        pipeline.addLast("handler", createHexDumpProxyHandler());
    }
    public abstract ChannelHandler   createHexDumpProxyHandler();

}
