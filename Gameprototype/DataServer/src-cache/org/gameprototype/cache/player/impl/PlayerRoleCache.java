package org.gameprototype.cache.player.impl;

import java.util.List;

import org.gameprototype.base.redis.KeysTools;
import org.gameprototype.base.redis.RedisTools;
import org.gameprototype.cache.player.IPlayerRoleCache;
import org.gameprototype.dao.IPlayerRoleDAO;
import org.gameprototype.dao.impl.PlayerRoleDAOImpl;
import org.gameprototype.dao.model.PlayerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author echo.ch
 *
 */
@Service("playerRoleCache")
public class PlayerRoleCache implements IPlayerRoleCache{

	@Autowired
	private RedisTools redisTools;
	@Autowired
	private IPlayerRoleDAO playerRoleDAO;
	@Override
	public PlayerRole getPlayerRoleById(Integer playerRoleId) {
		// TODO Auto-generated method stub
		String playerRoleIdKey=KeysTools.PlayerKeys.playerRoleIDKey(playerRoleId);
		PlayerRole playerRole=(PlayerRole) redisTools.OBJECTS.get(playerRoleIdKey);
		if(playerRole==null){
			playerRole=playerRoleDAO.getPlayerRole(playerRoleId);
			if(playerRole!=null){
				redisTools.OBJECTS.set(playerRoleIdKey, playerRole);
			}
		}
		return playerRole;
	}

	@Override
	public PlayerRole getPlayerRoleByName(String name) {
		// TODO Auto-generated method stub
		
		String playerRoleNameKey=KeysTools.PlayerKeys.playerRoleNameKey(name);
		PlayerRole playerRole=(PlayerRole) redisTools.OBJECTS.get(playerRoleNameKey);
		if(playerRole==null){
			playerRole=playerRoleDAO.getPlayerRole(name);
			if(playerRole!=null){
				redisTools.OBJECTS.set(playerRoleNameKey, playerRole);
			}
		}
		return playerRole;
		
	}

	@Override
	public int updatePlayerRole(PlayerRole playerRole) {
		// TODO Auto-generated method stub
		Integer id = playerRole.getId();
		String playerRoleKey=KeysTools.PlayerKeys.playerRoleIDKey(id);
		
		int i = playerRoleDAO.updateRoleById(playerRole);
		if(i>0){
			playerRole=playerRoleDAO.getPlayerRole(id);
			redisTools.OBJECTS.set(playerRoleKey, playerRole);
			
		}
		
		return i;
	}

	@Override
	public int addPlayerRole(PlayerRole playerRole) {
		// TODO Auto-generated method stub
		
		Boolean b =playerRoleDAO.addPlayerRole( playerRole);
		return b?1:0;
	}

	@Override
	public List<PlayerRole> getPlayRoleList(Integer accId) {
		// TODO Auto-generated method stub
		List<PlayerRole> list = playerRoleDAO.getPlayerRoleList(accId);
		return list;
	}

}
