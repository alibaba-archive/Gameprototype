package org.gameprototype.logic;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.protobuf.GeneratedMessage;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;
import org.gameprototype.proto.src.SocketModelDef;

/**
 * Created by zhoubo on 15-9-6.
 */
public abstract class AbstractHandlerServImpl {

    public Logger logger = Logger.getLogger(getClass());
    private Map<Integer, Channel> rolesSession = new ConcurrentHashMap<Integer, Channel>();
    private List<Integer> removeList = new CopyOnWriteArrayList<Integer>();

    private int type;
    private int area;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @SuppressWarnings("rawtypes")
    public void messageReceived(Channel channel, SocketModelDef.SocketModelPB message) {
        process(channel, message);
    }

    @SuppressWarnings("rawtypes")
    protected abstract void process(Channel channel, SocketModelDef.SocketModelPB message);

    /**
     * 判断是否已经进入
     *
     * @param userId
     * @return
     */
    public boolean enterd(Integer userId) {
        if (userId == null) {
            return false;
        }
        return rolesSession.containsKey(userId);
    }

    /**
     * 区域群发
     *
     * @param command 协议
     * @param message 消息体
     */
    @SuppressWarnings("rawtypes")
    public void broadcast(int command, GeneratedMessage message) {
        SocketModelDef.SocketModelPB sm = createSocketModel(getType(), getArea(), command, message);
        for (Channel channel : rolesSession.values()) {
            channel.writeAndFlush(sm);
        }
    }


    /**
     * 区域群发 排除指定用户
     *
     * @param exChannel 排除的用户Channel
     * @param command   协议
     * @param message   消息体
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public void broadcast(Channel exChannel, int command, GeneratedMessage message) {
        SocketModelDef.SocketModelPB sm = createSocketModel(getType(), getArea(), command, message);
        for (Channel channel : rolesSession.values()) {
            if (channel != exChannel) {
                channel.writeAndFlush(sm);
            }
        }
    }


    /**
     * 发送消息给用户
     *
     * @param channel 用户channel
     * @param command 协议
     * @param message 消息体
     */
    public void write(Channel channel, int command, Object message) {
        // TODO
    }

    /**
     * 发送消息给用户
     *
     * @param channel 用户channel
     * @param command 协议
     * @param message 消息体
     */
    public void write(Channel channel, int command, GeneratedMessage message) {
        channel.writeAndFlush(createSocketModel(getType(), getArea(), command, message));
    }

    /**
     * 发送消息给用户
     *
     * @param channel 用户channel
     * @param area    区域码
     * @param command 协议
     * @param message 消息体
     */
    public void write(Channel channel, int area, int command, GeneratedMessage message) {
        channel.writeAndFlush(createSocketModel(getType(), area, command, message));
    }

    /**
     * 发送消息给用户
     *
     * @param channel 用户channel
     * @param type    模块
     * @param area    区域码
     * @param command 协议
     * @param message 消息体
     */
    public void write(Channel channel, int type, int area, int command, GeneratedMessage message) {
        channel.writeAndFlush(createSocketModel(type, area, command, message));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected SocketModelDef.SocketModelPB createSocketModel(int type, int area, int command, GeneratedMessage msg) {
        SocketModelDef.SocketModelPB.Builder builder = SocketModelDef.SocketModelPB.newBuilder();
        builder.setType(type);
        builder.setArea(area);
        builder.setCommand(command);
        builder.setContent(msg.toByteString());
        return builder.build();
    }

    public void Colse(Channel channel) {
        channelClose(channel);
    }

    protected abstract void channelClose(Channel channel);

    public Channel getChannel(Long userId) {
        return rolesSession.get(userId);
    }

    protected void onShake(Channel channel) {
        //默认不做任何事情---有操作 自己实现克
    }
}
