package org.gameprototype.logic;

import io.netty.channel.Channel;
import org.gameprototype.proto.src.SocketModelDef;

/**
 * Created by zhoubo on 15-9-1.
 */
public interface IoHandler {

    /**
     * 消息到达
     */
    @SuppressWarnings("rawtypes")
    public void messageReceived(Channel channel, SocketModelDef.SocketModelPB message);

    /**
     * 连接关闭
     */
    public void channelClose(Channel channel);

    /**
     * 握手成功
     */
    public void onShake(Channel channel);
}
