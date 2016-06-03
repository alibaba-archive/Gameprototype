package org.gameprototype.dao.impl;

import java.util.List;

import org.gameprototype.dao.IPlayerRoleDAO;
import org.gameprototype.dao.mapper.PlayerRoleMapper;
import org.gameprototype.dao.model.PlayerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PlayerRoleDAO")
public class PlayerRoleDAOImpl implements IPlayerRoleDAO {

	@Autowired
	private PlayerRoleMapper playerRoleMapper;

	@Override
	public boolean addPlayerRole(PlayerRole playerRole) {
		// TODO Auto-generated method stub
		int i = playerRoleMapper.insert(playerRole);
		return i > 0;
	}

	@Override
	public boolean deletePlayerRole(Integer accountId, Integer playerRoleId) {
		// TODO Auto-generated method stub
		// PlayerRole playerRole = new PlayerRole();
		// playerRole.setAccountId(accountId);
		// playerRole.setId(playerRoleId);
		int i = playerRoleMapper.deleteByPrimaryKey(playerRoleId);
		return i > 0;
	}

	@Override
	public boolean enablePlayerRole(Integer playerRoleId) {
		// TODO Auto-generated method stub
		PlayerRole playerRole = new PlayerRole();
		playerRole.setId(playerRoleId);
		playerRole.setStatus(1001);
		int i = playerRoleMapper.updateByPrimaryKeySelective(playerRole);
		return i > 0;
	}

	@Override
	public boolean disablePlayerRole(Integer playerRoleId) {
		// TODO Auto-generated method stub
		PlayerRole playerRole = new PlayerRole();
		playerRole.setId(playerRoleId);
		playerRole.setStatus(1000);
		int i = playerRoleMapper.updateByPrimaryKeySelective(playerRole);
		return i > 0;
	}

	@Override
	public boolean updatePlayerRoleHitpoints(Integer playerRoleId, Integer hitpoints) {
		// TODO Auto-generated method stub
		PlayerRole playerRole = new PlayerRole();
		playerRole.setId(playerRoleId);
		playerRole.setHitpoints(hitpoints);
		int i = playerRoleMapper.updateByPrimaryKeySelective(playerRole);
		return i > 0;
	}

	@Override
	public List<PlayerRole> getPlayerRoleList(Integer accountId) {
		// TODO Auto-generated method stub
		PlayerRole playerRole = new PlayerRole();
		playerRole.setAccountId(accountId);
		return playerRoleMapper.selectList(playerRole);
	}

	@Override
	public Integer isOnline(Integer accountId) {
		// TODO Auto-generated method stub
		return playerRoleMapper.isOnline(accountId);
	}

	@Override
	public PlayerRole getPlayerRole(Integer playerid) {
		// TODO Auto-generated method stub
		PlayerRole playerRole = playerRoleMapper.selectByPrimaryKey(playerid);
		return playerRole;
	}

	@Override
	public PlayerRole getPlayerRole(String playerName) {
		// TODO Auto-generated method stub
		PlayerRole playerRole = playerRoleMapper.selectPlayerRoleByName(playerName);
		return playerRole;
	}

	@Override
	public int updateRoleByName(PlayerRole record) {
		// TODO Auto-generated method stub
		int i = playerRoleMapper.updateRoleByName(record);
		return i;
	}

	@Override
	public int updateRoleById(PlayerRole record) {
		// TODO Auto-generated method stub
		int i = playerRoleMapper.updateByPrimaryKeySelective(record);
		return i;
	}

}
