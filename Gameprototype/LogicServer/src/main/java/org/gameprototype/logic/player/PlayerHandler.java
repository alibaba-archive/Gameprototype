package org.gameprototype.logic.player;

import io.netty.channel.Channel;
import org.gameprototype.logic.IHandlerServ;
import org.gameprototype.logic.IoHandler;
import org.gameprototype.logic.player.protocol.PlayerProtocol;
import org.gameprototype.manager.interfaces.IhandlerManager;
import org.gameprototype.proto.src.SocketModelDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by haihong.xiahh on 2015/9/8.
 */

@Service("playerHandler")
public abstract class PlayerHandler implements IoHandler {
    @Autowired
    private IHandlerServ playerHandlerImpl;

    /**
     * Created by zhoubo
     *
     * @return
     */

    //   private IHandlerServ character;
    private IHandlerServ equiHandlerImpl;
    private IHandlerServ item;

    private IhandlerManager manager;

    @PostConstruct
    void init() {
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void messageReceived(Channel channel, SocketModelDef.SocketModelPB message) {
        switch (message.getArea()) {
            case PlayerProtocol.AREA_EQUI:
                equiHandlerImpl.messageReceived(channel, message);
                break;
            case PlayerProtocol.AREA_CHARACTER:
                playerHandlerImpl.messageReceived(channel, message);
                break;
            case PlayerProtocol.AREA_ITEM:
                item.messageReceived(channel, message);
                break;
        }
        manager.get(message.getArea()).messageReceived(channel, message);
    }

    @Override
    public void channelClose(Channel channel) {
        manager.get(PlayerProtocol.AREA_CHARACTER).close(channel);
        manager.get(PlayerProtocol.AREA_EQUI).close(channel);
    }

    public abstract IHandlerServ createPlayerHandler();

    public abstract IHandlerServ createTeamHandler();


    @Resource(name = "userManager")
    protected void setManager(IhandlerManager manager) {
        this.manager = manager;
    }

    @Override
    public void onShake(Channel channel) {
        //

    }
}
