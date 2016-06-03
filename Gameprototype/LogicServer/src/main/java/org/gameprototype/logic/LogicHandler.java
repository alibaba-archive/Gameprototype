package org.gameprototype.logic;

import io.netty.channel.Channel;
import org.apache.log4j.Logger;
import org.gameprototype.event.model.OnShakeEvent;
import org.gameprototype.logic.login.protocol.LoginProtocol;
import org.gameprototype.nettyhandler.HeartBeatHandler;
import org.gameprototype.proto.src.SocketModelDef;
import org.gameprototype.protocol.Protocol;
import org.gameprototype.tool.impl.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import javax.annotation.Resource;

/**
 * Created by zhoubo on 15-9-1.
 */
public class LogicHandler implements IoHandler, ApplicationListener<OnShakeEvent> {
    private IoHandler login;

    @Autowired
    private IoHandler playerHandler;

    private Logger logger = Logger.getLogger(getClass());

    @Resource(name = "heartBeatHandler")
    private HeartBeatHandler heartBeatHandler;

    @Override
    public void onApplicationEvent(OnShakeEvent event) {
        Channel channel = event.getChannel();
        login.onShake(channel);

    }


    @SuppressWarnings("rawtypes")
    @Override
    public void messageReceived(Channel channel, SocketModelDef.SocketModelPB message) {
        try {
            switch (message.getType()) {
                case Protocol.TYPE_LOGIN:
                    login.messageReceived(channel, message);
                    if (message.getCommand() == LoginProtocol.LOGIN_CREQ) {
                        heartBeatHandler.received(channel);
                    }
                    break;
                case Protocol.TYPE_PLAYER:
                    playerHandler.messageReceived(channel, message);
            }

        } catch (Exception e) {
            logger.error(ErrorUtil.decode(e));
        }

    }

    @Override
    public void channelClose(Channel channel) {
        // 下面三个必须最后调用---因为会清除channel与角色的映射关系
        login.channelClose(channel);
        heartBeatHandler.close(channel);
    }

    @Override
    public void onShake(Channel channel) {
        // 总逻辑控制 无需监听 --握手由此class派发

    }

    public IoHandler getLogin() {
        return login;
    }

    public void setLogin(IoHandler login) {
        this.login = login;
    }


    public HeartBeatHandler getHeartBeatHandler() {
        return heartBeatHandler;
    }

    public void setHeartBeatHandler(HeartBeatHandler heartBeatHandler) {
        this.heartBeatHandler = heartBeatHandler;
    }
}
