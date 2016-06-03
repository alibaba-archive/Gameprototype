package org.gameprototype.dao;

import org.gameprototype.dao.model.PlayerTeam;

/**
 * Created by haihong.xiahh on 2015/9/21.
 */
public interface IPlayerTeamDAO {
    int insertPlayerTeam(PlayerTeam playerTeam); // 新增组队信息，创建队伍
    int updatePlayerTeam(PlayerTeam playerTeam); //更新组队信息，解散队伍
}
