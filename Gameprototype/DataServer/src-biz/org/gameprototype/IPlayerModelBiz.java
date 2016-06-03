package org.gameprototype;

import java.util.List;

import org.gameprototype.dao.model.PlayerModel;

public interface IPlayerModelBiz {
	PlayerModel get(Integer playerModelId);// 获取角色信息

	List<PlayerModel> getList();// 获取所有角色列表
}
