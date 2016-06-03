package org.gameprototype.logic.login;

import io.netty.channel.Channel;
import org.gameprototype.logic.IHandlerServ;
import org.gameprototype.logic.IoHandler;
import org.gameprototype.proto.src.SocketModelDef;

/**
 * Created by zhoubo on 15-9-6.
 */
public class LoginHandler implements IoHandler {

    private IHandlerServ loginHandlerImpl;

    @SuppressWarnings("rawtypes")
    @Override
    public void messageReceived(Channel channel, SocketModelDef.SocketModelPB message) {

        loginHandlerImpl.messageReceived(channel, message);

    }

    @Override
    public void channelClose(Channel channel) {

        loginHandlerImpl.close(channel);

    }

    @Override
    public void onShake(Channel channel) {


    }

    public IHandlerServ getLoginHandlerImpl() {
        return loginHandlerImpl;
    }

    public void setLoginHandlerImpl(IHandlerServ loginHandlerImpl) {
        this.loginHandlerImpl = loginHandlerImpl;
    }
}
