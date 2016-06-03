package org.gameprototype.cache.player;

import org.gameprototype.dao.model.PlayerTeam;

/**
 * Created by haihong.xiahh on 2015/9/21.
 */
public interface IPlayerTeamCache {
	
	int insertPlayerTeam(PlayerTeam playerTeam); // 新增组队信息，创建队伍
	int deletePlayerTeam(PlayerTeam playerTeam); //更新组队信息，解散队伍
	boolean onlineTeam(Integer playerId); //查询是否组队
}
