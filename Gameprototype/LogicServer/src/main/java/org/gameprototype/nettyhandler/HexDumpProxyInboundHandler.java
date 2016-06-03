package org.gameprototype.nettyhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.apache.log4j.Logger;
import org.gameprototype.logic.LogicHandler;
import org.gameprototype.logic.player.PlayerHandler;
import org.gameprototype.proto.src.SocketModelDef;

/**
 * Created by zhoubo on 15-9-1.
 */
public class HexDumpProxyInboundHandler extends ChannelInboundHandlerAdapter {

    Logger logger=Logger.getLogger(getClass());

    private LogicHandler logic;
   
    private PlayerHandler player;

    public PlayerHandler getPlayer() {
		return player;
	}

	public void setPlayer(PlayerHandler player) {
		this.player = player;
	}

	public LogicHandler getLogic() {
        return logic;
    }

    public void setLogic(LogicHandler logic) {
        this.logic = logic;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("用户连接");
        //   ctx.fireChannelActive();
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //   ctx.fireChannelInactive();
        logger.debug("channel inactive:"+ctx.channel());
        logic.channelClose(ctx.channel());
    }


    @SuppressWarnings("rawtypes")
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //   ctx.fireChannelRead(msg);
        SocketModelDef.SocketModelPB message=(SocketModelDef.SocketModelPB)msg;
      
        logic.messageReceived(ctx.channel(), message);
        logger.debug("channel read");
        logger.debug(message.toString());
        logger.debug("getSerializedSize:" + message.getSerializedSize());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        //     ctx.fireExceptionCaught(cause);
    }
}
