package org.gameprototype.impl;

import java.util.List;

import org.gameprototype.IPlayerRoleBiz;
import org.gameprototype.cache.player.IPlayerModelCache;
import org.gameprototype.cache.player.IPlayerRoleCache;
import org.gameprototype.dao.model.PlayerModel;
import org.gameprototype.dao.model.PlayerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("playerRoleBiz")
public class PlayerRoleBiz implements IPlayerRoleBiz {

	@Autowired
	private IPlayerRoleCache playerRoleCache;

	@Override
	public List<PlayerRole> getPlayerRoleList(Integer accId) {
		// TODO Auto-generated method stub
		return playerRoleCache.getPlayRoleList(accId);
	}

}
