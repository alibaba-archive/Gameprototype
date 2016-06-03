package org.gameprototype.logic.player.impl;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.Channel;
import org.gameprototype.IPlayerEquipBiz;
import org.gameprototype.logic.AbstractHandlerServImpl;
import org.gameprototype.logic.IHandlerServ;
import org.gameprototype.logic.player.protocol.EquiProtocol;
import org.gameprototype.proto.src.*;
import org.gameprototype.tool.IExecutorPool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by haihong.xiahh on 2015/10/19.
 */
@Service("equipHandlerImpl")
public class EquipHandlerServImpl extends AbstractHandlerServImpl implements IHandlerServ {
    @Resource(name = "executorPool")
    private IExecutorPool executorPool;

    @Resource(name = "playerEquipBiz")
    private IPlayerEquipBiz playerEquipBiz;


    protected void process(Channel channel, SocketModelDef.SocketModelPB message) {
        switch (message.getCommand()) {
            case EquiProtocol.GET_LIST_CREQ:
                getList(channel, message);
                break;
            case EquiProtocol.GET_DETAIL_CREQ:
                getDetail(channel, message);
                break;
            case EquiProtocol.INSERT_EQUIP_CREQ:
                insertQuip(channel, message);
            case EquiProtocol.DELETE_EQUIP_CREQ:
                deleteQuip(channel, message);
        }

    }

    private void getList(final Channel channel, SocketModelDef.SocketModelPB message) {
        PlayerRoleDTODef.PlayerRoleDTOPB playerRoleDTO = null;
        try {
            playerRoleDTO = PlayerRoleDTODef.PlayerRoleDTOPB.parseFrom(message.getContent());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        final PlayerRoleDTODef.PlayerRoleDTOPB finalPlayerRoleDTO = playerRoleDTO;
        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                final Integer playerRoleId;
                if (finalPlayerRoleDTO != null) {
                    playerRoleId = (int) finalPlayerRoleDTO.getPlayerRoleId();
                }
                //write(channel, EquiProtocol.GET_LIST_SRES, playerEquipBiz.getList(playerRoleId));
            }
        });
    }

    private void getDetail(final Channel channel, SocketModelDef.SocketModelPB message) {
        PlayerEquipDTODef.PlayerEquipDTOPB playerEquipDTO = null;
        try {
            playerEquipDTO = PlayerEquipDTODef.PlayerEquipDTOPB.parseFrom(message.getContent());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        final PlayerEquipDTODef.PlayerEquipDTOPB finalPlayerEquipDTO = playerEquipDTO;
        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                final Integer playerEquipId;
                if (finalPlayerEquipDTO != null) {
                    playerEquipId = (int) finalPlayerEquipDTO.getPlayerEquipId();
                }
                //write(channel, EquiProtocol.GET_DETAIL_SRES, playerEquipBiz.getDetail(playerEquipId));
            }
        });
    }

    private void insertQuip(final Channel channel, SocketModelDef.SocketModelPB message) {
        PlayerEquipDTODef.PlayerEquipDTOPB playerEquipDTO = null;
        try {
            playerEquipDTO = PlayerEquipDTODef.PlayerEquipDTOPB.parseFrom(message.getContent());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        final PlayerEquipDTODef.PlayerEquipDTOPB finalPlayerEquipDTO = playerEquipDTO;
        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                CommonRespDef.CommonResp.RespCode respCode = CommonRespDef.CommonResp.RespCode.PARSE_REQ_PARAM_ERROR;
                if (finalPlayerEquipDTO != null) {
                    Integer playerRoleId = (int) finalPlayerEquipDTO.getPlayerRoleId();
                    Integer equipId = (int) finalPlayerEquipDTO.getPlayerEquipId();
                    if (playerEquipBiz.insert(playerRoleId, equipId) > 0) {
                        respCode = CommonRespDef.CommonResp.RespCode.SUCCESS;
                    } else {
                        respCode = CommonRespDef.CommonResp.RespCode.INTERNAL_ERROR;
                    }
                }
                write(channel, EquiProtocol.INSERT_EQUIP_SRES, ProtoHelper.genCommonResp(respCode));

            }
        });
    }

    private void deleteQuip(final Channel channel, SocketModelDef.SocketModelPB message) {
        PlayerEquipDTODef.PlayerEquipDTOPB playerEquipDTO = null;
        try {
            playerEquipDTO = PlayerEquipDTODef.PlayerEquipDTOPB.parseFrom(message.getContent());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        final PlayerEquipDTODef.PlayerEquipDTOPB finalPlayerEquipDTO = playerEquipDTO;
        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                CommonRespDef.CommonResp.RespCode respCode = CommonRespDef.CommonResp.RespCode.PARSE_REQ_PARAM_ERROR;
                if (finalPlayerEquipDTO != null) {
                    Integer playerRoleId = (int) finalPlayerEquipDTO.getPlayerRoleId();
                    Integer playerEquipId = (int) finalPlayerEquipDTO.getPlayerEquipId();
                    Integer equipId = (int) finalPlayerEquipDTO.getEquipId();
                    if (playerEquipBiz.delete(playerEquipId, playerRoleId, equipId) > 0) {
                        respCode = CommonRespDef.CommonResp.RespCode.SUCCESS;
                    } else {
                        respCode = CommonRespDef.CommonResp.RespCode.INTERNAL_ERROR;
                    }
                }
                write(channel, EquiProtocol.DELETE_EQUIP_SRES, ProtoHelper.genCommonResp(respCode));

            }
        });
    }


    @Override
    protected void channelClose(Channel channel) {
        // TODO
    }

    @Override
    public void close(Channel channel) {
        // TODO
    }
}
