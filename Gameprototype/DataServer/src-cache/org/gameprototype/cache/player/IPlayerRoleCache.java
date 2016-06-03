package org.gameprototype.cache.player;

import java.util.List;

import org.gameprototype.dao.model.PlayerRole;
/**
 * 
 * @author echo.ch
 *
 */
public interface IPlayerRoleCache {
	
	public PlayerRole getPlayerRoleById(Integer playerRoleId);
	
	public PlayerRole getPlayerRoleByName(String name);
	
	public int updatePlayerRole(PlayerRole playerRole);
	
	public int addPlayerRole(PlayerRole playerRole);
	
	public List<PlayerRole> getPlayRoleList(Integer accId);
}
