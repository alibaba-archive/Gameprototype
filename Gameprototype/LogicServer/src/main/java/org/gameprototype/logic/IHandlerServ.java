package org.gameprototype.logic;

import io.netty.channel.Channel;
import org.gameprototype.proto.src.SocketModelDef;

/**
 * Created by zhoubo on 15-9-1.
 */
public interface IHandlerServ {

    /**
     * 设置区域码（二级协议）
     */
    public void setArea(int area);

    /**
     * 连接关闭
     */
    public void close(Channel channel);

    /**
     * 消息达到
     */
    @SuppressWarnings("rawtypes")
    public void messageReceived(Channel channel, SocketModelDef.SocketModelPB message);
}
