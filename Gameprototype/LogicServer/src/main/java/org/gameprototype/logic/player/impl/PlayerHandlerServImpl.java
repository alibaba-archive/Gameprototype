package org.gameprototype.logic.player.impl;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.Channel;

import javax.annotation.Resource;

import org.gameprototype.IGameMapBiz;

import org.gameprototype.IPlayerModelBiz;
import org.gameprototype.IPlayerRoleBiz;
import org.gameprototype.IPlayerTeamBiz;
import org.gameprototype.dao.model.PlayerTeam;
import org.gameprototype.logic.AbstractHandlerServImpl;
import org.gameprototype.logic.IHandlerServ;

import org.gameprototype.logic.player.protocol.CharacterProtocol;

import org.gameprototype.proto.src.*;
import org.gameprototype.tool.IExecutorPool;
import org.springframework.stereotype.Service;

/**
 * Created by haihong.xiahh on 2015/9/8.
 */
@Service("playerHandlerImpl")
public class PlayerHandlerServImpl extends AbstractHandlerServImpl implements IHandlerServ {

    @Resource(name = "executorPool")
    private IExecutorPool executorPool;

    @Resource
    private IPlayerRoleBiz playerRoleBiz;

    @Resource
    private IPlayerTeamBiz playerTeamBiz;

    @Resource
    private IPlayerModelBiz playerModelBiz;

    @Resource
    private IGameMapBiz gameMapBiz;

    protected void process(Channel channel, SocketModelDef.SocketModelPB message) {
        /**
         * //获取场景列表 public static final int GET_LIST_CREQ = 0x1; //获取副本列表 public
         * static final int GET_LIST_SRES = 0x2; // 进入场景 public static final int
         * ENTER_CREQ = 0x3; // 进入副本 public static final int ENTER_SRES = 0x4;
         * public static final int ENTER_BRO = 0x5; //获取角色列表 public static final
         * int GET_LIST_PLAY = 0x6; //战斗 public static final int FIGHT_MON=0x7;
         * //组队 public static final int ADD_TERM=0x9; //离开队伍 public static final
         * int LEAVE_TERM=0x10;
         */
        switch (message.getCommand()) {
            case CharacterProtocol.GET_LIST_MAP:
                getGameMapList(channel, message);
                break;
            case CharacterProtocol.GET_LIST_SRES:
                getSresList(channel, message);
                break;
            case CharacterProtocol.ENTER_MAP:
                enterGameMap(channel, message);
                break;
            case CharacterProtocol.ENTER_SRES:
                enterSres(channel, message);
                break;
            case CharacterProtocol.ENTER_BRO:
                enterBro(channel, message);
                break;
            case CharacterProtocol.GET_LIST_PLAY:
                getListPlay(channel, message);
                break;
            case CharacterProtocol.FIGHT_MON:
                fightMon(channel, message);
                break;
            case CharacterProtocol.ADD_TEAM:
                addTerm(channel, message);
                break;
            case CharacterProtocol.LEAVE_TEAM:
                leaveTerm(channel, message);
                break;
            case CharacterProtocol.GET_LIST_MODEl:
                getListModel(channel, message);
                break;
            case CharacterProtocol.LEAVE_MAP:
                leaveMap(channel, message);
                break;
            // GET_LIST_MAPROLE
            case CharacterProtocol.GET_LIST_MAPROLE:
                getMapRoleList(channel, message);
                break;
        }

    }

    /**
     * //获取场景列表 public static final int GET_LIST_CREQ = 0x1;//获取副本列表 public
     * static final int GET_LIST_SRES = 0x2;// 进入场景 public static final int
     * ENTER_CREQ = 0x3;// 进入副本 public static final int ENTER_SRES = 0x4; public
     * static final int ENTER_BRO = 0x5;//获取角色列表 public static final int
     * GET_LIST_PLAY = 0x6;//得到角色列表 public static final int FIGHT_MON=0x7;//战斗
     * public static final int ADD_TERM=0x9;//组队 public static final int
     * LEAVE_TERM=0x10;//离开队伍
     */
    public void getGameMapList(final Channel channel, SocketModelDef.SocketModelPB message) {

        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                //write(channel, CharacterProtocol.GET_LIST_MAP, gameMapBiz.getMapList());
            }
        });
    }

    public void getSresList(final Channel channel, SocketModelDef.SocketModelPB message) {

    }

    public void enterGameMap(final Channel channel, SocketModelDef.SocketModelPB message) {
        CharacterDTODef.CharacterDTOPB characterDTO = null;
        try {
            characterDTO = CharacterDTODef.CharacterDTOPB.parseFrom(message.getContent());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        final CharacterDTODef.CharacterDTOPB finalCharacterDTO = characterDTO;
        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                CommonRespDef.CommonResp.RespCode respCode = CommonRespDef.CommonResp.RespCode.PARSE_REQ_PARAM_ERROR;
                if (finalCharacterDTO != null) {
                    Integer playerId = (int) finalCharacterDTO.getPlayerId();
                    Integer mapId = (int) finalCharacterDTO.getMapId();
                    if (gameMapBiz.updateMapRole(mapId, playerId) > 0) {
                        respCode = CommonRespDef.CommonResp.RespCode.SUCCESS;
                    } else {
                        respCode = CommonRespDef.CommonResp.RespCode.INTERNAL_ERROR;
                    }
                }
                write(channel, CharacterProtocol.ENTER_MAP, ProtoHelper.genCommonResp(respCode));
            }
        });
    }

    public void enterSres(final Channel channel, SocketModelDef.SocketModelPB message) {

    }

    public void enterBro(final Channel channel, SocketModelDef.SocketModelPB message) {

    }

    public void getListPlay(final Channel channel, SocketModelDef.SocketModelPB message) {
        CharacterDTODef.CharacterDTOPB characterDTO = null;
        try {
            characterDTO = CharacterDTODef.CharacterDTOPB.parseFrom(message.getContent());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        final CharacterDTODef.CharacterDTOPB finalCharacterDTO = characterDTO;
        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                if (finalCharacterDTO != null) {
                    Integer accountId = (int) finalCharacterDTO.getAccountId();
                }
                // TODO gen related proto
                //write(channel, CharacterProtocol.GET_LIST_PLAY, playerRoleBiz.getPlayerRoleList(accountId));
            }
        });
    }

    public void fightMon(final Channel channel, SocketModelDef.SocketModelPB message) {

    }

    public void createTerm(final Channel channel, SocketModelDef.SocketModelPB message) {
        PlayerTeamDTODef.PlayerTeamDTOPB playerTeamDTO = null;
        try {
            playerTeamDTO = PlayerTeamDTODef.PlayerTeamDTOPB.parseFrom(message.getContent());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        final PlayerTeamDTODef.PlayerTeamDTOPB finalPlayerTeamDTO = playerTeamDTO;
        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                CommonRespDef.CommonResp.RespCode respCode = CommonRespDef.CommonResp.RespCode.PARSE_REQ_PARAM_ERROR;
                PlayerTeam playerTeam = new PlayerTeam();
                if (finalPlayerTeamDTO != null) {
                    playerTeam.setMemberId(finalPlayerTeamDTO.getMemberId());
                    playerTeam.setLeaderId((int) finalPlayerTeamDTO.getLeaderId());
                    if (playerTeamBiz.insertPlayerTeam(playerTeam) > 0) {
                        respCode = CommonRespDef.CommonResp.RespCode.SUCCESS;
                    } else {
                        respCode = CommonRespDef.CommonResp.RespCode.INTERNAL_ERROR;
                    }
                }
                write(channel, CharacterProtocol.CREATE_TEAM, ProtoHelper.genCommonResp(respCode));
            }
        });
    }

    public void addTerm(final Channel channel, SocketModelDef.SocketModelPB message) {

    }

    public void leaveTerm(final Channel channel, SocketModelDef.SocketModelPB message) {

    }

    public void getListModel(final Channel channel, SocketModelDef.SocketModelPB message) {
        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                // TODO gen related proto
                //write(channel, CharacterProtocol.GET_LIST_MODEl, playerModelBiz.getList());
            }
        });
    }

    public void leaveMap(final Channel channel, SocketModelDef.SocketModelPB message) {
        CharacterDTODef.CharacterDTOPB characterDTO = null;
        try {
            characterDTO = CharacterDTODef.CharacterDTOPB.parseFrom(message.getContent());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        final CharacterDTODef.CharacterDTOPB finalCharacterDTO = characterDTO;
        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                CommonRespDef.CommonResp.RespCode respCode = CommonRespDef.CommonResp.RespCode.PARSE_REQ_PARAM_ERROR;
                if (finalCharacterDTO != null) {
                    Integer playerId = (int) finalCharacterDTO.getPlayerId();
                    Integer mapId = (int) finalCharacterDTO.getMapId();
                    if (gameMapBiz.deleteMapRole(mapId, playerId) > 0) {
                        respCode = CommonRespDef.CommonResp.RespCode.SUCCESS;
                    } else {
                        respCode = CommonRespDef.CommonResp.RespCode.INTERNAL_ERROR;
                    }
                }

                write(channel, CharacterProtocol.LEAVE_MAP, ProtoHelper.genCommonResp(respCode));
            }
        });
    }

    public void getMapRoleList(final Channel channel, SocketModelDef.SocketModelPB message) {
        CharacterDTODef.CharacterDTOPB characterDTO = null;
        try {
            characterDTO = CharacterDTODef.CharacterDTOPB.parseFrom(message.getContent());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }


        final CharacterDTODef.CharacterDTOPB finalCharacterDTO = characterDTO;
        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                final Integer mapId;
                if (finalCharacterDTO != null) {
                    mapId = (int) finalCharacterDTO.getMapId();
                }
                //write(channel, CharacterProtocol.GET_LIST_MAPROLE, gameMapBiz.getMapRoleList(mapId));
            }
        });
    }


    @Override
    protected void channelClose(Channel channel) {

    }

    @Override
    public void close(Channel channel) {

    }
}
