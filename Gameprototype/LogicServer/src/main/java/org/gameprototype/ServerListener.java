package org.gameprototype;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.gameprototype.nettyhandler.HexDumpProxyPipelineFactory;

import javax.annotation.Resource;

/**
 * Created by zhoubo on 15-9-1.
 */
public class ServerListener {

    private static Logger logger=Logger.getLogger(ServerListener.class);

    @Resource(name="startBoots")
    public ServerBootstrap server;

    private int port;

    public ServerListener(int port){
        this.port=port;

    }

    private ChannelFuture serverCF;

    public void init(){
        server.channel(NioServerSocketChannel.class)
                .group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .localAddress(port)
                .childHandler(hexDumpProxyPipelineFactory)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_RCVBUF, 1024)
                .option(ChannelOption.SO_SNDBUF, 1024)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100000);
        try {
            serverCF= server.bind().sync();
            logger.info("listening " + port);
            //	f.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        serverCF.channel().close();
    }

    @Resource(name="hexDumpProxyPipelineFactory")
    private HexDumpProxyPipelineFactory hexDumpProxyPipelineFactory;
}
