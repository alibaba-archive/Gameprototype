package org.gameprototype.cache.player.impl;

import org.gameprototype.base.redis.RedisTools;
import org.gameprototype.cache.player.IPlayerTeamCache;
import org.gameprototype.dao.IPlayerTeamDAO;
import org.gameprototype.dao.model.PlayerRole;
import org.gameprototype.dao.model.PlayerStatus;
import org.gameprototype.dao.model.PlayerTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by haihong.xiahh on 2015/9/21.
 */
@Service("playerTeamCache")
public class PlayerTeamCache implements IPlayerTeamCache {

    @Autowired
    private IPlayerTeamDAO playerTeamDAO;

    @Autowired
    private RedisTools redisTools;

    @Autowired
    private PlayerRoleCache playerRoleCache;

    @Override
    public int insertPlayerTeam(PlayerTeam playerTeam) {
        // TODO Auto-generated method stub
        String members[] = playerTeam.getMemberId().split(",");
        for (int i = 0; i < members.length; i++) {
            // String memberKey =
            // KeysTools.PlayerKeys.playerRoleIDKey(Integer.parseInt(members[i]));
            PlayerRole playerRole = playerRoleCache.getPlayerRoleById(Integer
                    .parseInt(members[i]));
            if (playerRole.getStatus() == PlayerStatus.ONLINE_TEAM) {
                return -1;
            }
        }
        if (playerRoleCache.getPlayerRoleById(playerTeam.getLeaderId()).getStatus() == PlayerStatus.ONLINE_TEAM) {
            return -1;
        }

        for (int i = 0; i < members.length; i++) {
            PlayerRole playerRole = playerRoleCache.getPlayerRoleById(Integer
                    .parseInt(members[i]));
            playerRole.setStatus(PlayerStatus.ONLINE_TEAM);
            playerRoleCache.updatePlayerRole(playerRole);
        }

        PlayerRole playerRoleLeader = playerRoleCache.getPlayerRoleById(playerTeam
                .getLeaderId());
        playerRoleLeader.setStatus(PlayerStatus.ONLINE_TEAM);
        playerRoleCache.updatePlayerRole(playerRoleLeader);

        return playerTeamDAO.insertPlayerTeam(playerTeam);
    }; // 新增组队信息，创建队伍

    @Override
    public int deletePlayerTeam(PlayerTeam playerTeam) {
        PlayerRole playerRoleLeader = playerRoleCache.getPlayerRoleById(playerTeam
                .getLeaderId());
        playerRoleLeader.setStatus(PlayerStatus.ONLINE_SINGLE);
        playerRoleCache.updatePlayerRole(playerRoleLeader);

        String members[] = playerTeam.getMemberId().split(",");
        for (int i = 0; i < members.length; i++) {
            PlayerRole playerRole = playerRoleCache.getPlayerRoleById(Integer
                    .parseInt(members[i]));
            playerRole.setStatus(PlayerStatus.ONLINE_SINGLE);
            playerRoleCache.updatePlayerRole(playerRole);
        }

        return playerTeamDAO.updatePlayerTeam(playerTeam);

    }

    @Override
    public boolean onlineTeam(Integer playerId) {
        PlayerRole playerRole = playerRoleCache.getPlayerRoleById(playerId);
        return playerRole.getStatus() == PlayerStatus.ONLINE_TEAM;
    }

}
