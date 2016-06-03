package org.gameprototype.impl;

import java.util.List;

import org.gameprototype.IPlayerModelBiz;
import org.gameprototype.cache.player.IPlayerModelCache;
import org.gameprototype.dao.model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("playerModelBiz")
public class PlayerModelBiz implements IPlayerModelBiz {

	@Autowired
	private IPlayerModelCache playerModelCache;

	@Override
	public PlayerModel get(Integer playerModelId) {
		// TODO Auto-generated method stub
		PlayerModel p = playerModelCache.get(playerModelId);
		return p;
	}

	@Override
	public List<PlayerModel> getList() {
		// TODO Auto-generated method stub
		return playerModelCache.getList();
	}

}
