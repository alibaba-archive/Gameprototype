package org.gameprototype.logic.login.impl;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.Channel;
import org.gameprototype.IAccountBiz;
import org.gameprototype.logic.AbstractHandlerServImpl;
import org.gameprototype.logic.IHandlerServ;
import org.gameprototype.logic.login.protocol.LoginProtocol;
import org.gameprototype.proto.src.CommonRespDef;
import org.gameprototype.proto.src.LoginDTODef;
import org.gameprototype.proto.src.ProtoHelper;
import org.gameprototype.proto.src.SocketModelDef;
import org.gameprototype.protocol.Protocol;
import org.gameprototype.tool.IExecutorPool;

import javax.annotation.Resource;

/**
 * Created by zhoubo on 15-9-6.
 */

public class LoginHandlerServImpl extends AbstractHandlerServImpl implements IHandlerServ {


    @Resource(name = "executorPool")
    private IExecutorPool executorPool;

    @Resource(name = "accountBiz")
    private IAccountBiz accountBiz;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected void process(Channel channel, SocketModelDef.SocketModelPB message) {
        System.out.println(message.toString());
        switch (message.getCommand()) {
            case LoginProtocol.LOGIN_CREQ:
                login(channel, message);
                break;
            case LoginProtocol.CREATE_ACCOUNT_CREQ:
                createAccount(channel, message);
                break;
        }
    }

    private void login(final Channel channel, SocketModelDef.SocketModelPB message) {
        LoginDTODef.LoginDTOPB dto = null;
        try {
            dto = LoginDTODef.LoginDTOPB.parseFrom(message.getContent());
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            logger.error("parse msg failed");
        }
        final LoginDTODef.LoginDTOPB finalDto = dto;
        executorPool.execute(new Runnable() {

            @Override
            public void run() {
                CommonRespDef.CommonResp.RespCode loginCode = CommonRespDef.CommonResp.RespCode.INTERNAL_ERROR;
                if (finalDto != null) {
                    int loginRet = accountBiz.login(channel, finalDto.getAccount(), finalDto.getPassword());
                    if (loginRet > 0) {
                        loginCode = CommonRespDef.CommonResp.RespCode.SUCCESS;
                    } else {
                        loginCode = CommonRespDef.CommonResp.RespCode.valueOf(loginRet);
                    }

                }
                write(channel, LoginProtocol.LOGIN_SRES, ProtoHelper.genCommonResp(loginCode));
            }
        });
    }

    private void createAccount(final Channel channel, SocketModelDef.SocketModelPB message) {
        LoginDTODef.LoginDTOPB dto = null;
        try {
            dto = LoginDTODef.LoginDTOPB.parseFrom(message.getContent());
        } catch (InvalidProtocolBufferException e) {
            logger.error("parse msg failed");
        }
        final LoginDTODef.LoginDTOPB finalDto = dto;
        executorPool.execute(new Runnable() {

            @Override
            public void run() {
                CommonRespDef.CommonResp.RespCode loginCode = CommonRespDef.CommonResp.RespCode.INTERNAL_ERROR;
                if (finalDto != null) {
                    loginCode = CommonRespDef.CommonResp.RespCode.valueOf(accountBiz.create(finalDto.getAccount(), finalDto.getPassword()));
                }
                write(channel, LoginProtocol.CREATE_ACCOUNT_SRES, ProtoHelper.genCommonResp(loginCode));
            }
        });
    }

    @Override
    protected void channelClose(Channel channel) {

    }

    public int getType() {
        return Protocol.TYPE_LOGIN;
    }

    @Override
    public void close(Channel channel) {
        accountBiz.offline(channel);
    }

}
