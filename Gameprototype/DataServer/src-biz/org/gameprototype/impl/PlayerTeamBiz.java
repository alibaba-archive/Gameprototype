package org.gameprototype.impl;

import org.gameprototype.IPlayerTeamBiz;
import org.gameprototype.cache.player.IPlayerTeamCache;
import org.gameprototype.dao.model.PlayerTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("playerTeamBiz")
public class PlayerTeamBiz implements IPlayerTeamBiz {
	@Autowired
	private IPlayerTeamCache playerTeamCache;

	@Override
	public int insertPlayerTeam(PlayerTeam playerTeam) {
		// TODO Auto-generated method stub
		return playerTeamCache.insertPlayerTeam(playerTeam);
	}

}
