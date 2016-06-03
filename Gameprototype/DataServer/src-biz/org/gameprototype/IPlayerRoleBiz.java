package org.gameprototype;

import java.util.List;

import org.gameprototype.dao.model.PlayerModel;
import org.gameprototype.dao.model.PlayerRole;

public interface IPlayerRoleBiz {

	List<PlayerRole> getPlayerRoleList(Integer accId);
}
