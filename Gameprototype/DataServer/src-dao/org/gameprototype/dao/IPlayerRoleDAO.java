package org.gameprototype.dao;

import org.gameprototype.dao.model.PlayerRole;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/9/16.
 */
public interface IPlayerRoleDAO {
    //for PlayerRole 玩家角色信息
    boolean addPlayerRole(PlayerRole playerRole); //新增角色
    boolean deletePlayerRole(Integer accountId, Integer playerRoleId); //删除角色
    boolean enablePlayerRole(Integer playerRoleId); //激活角色
    boolean disablePlayerRole(Integer playerRoleId); //
    boolean updatePlayerRoleHitpoints(Integer playerRoleId, Integer hitpoints); //更新角色的生命力
    List<PlayerRole> getPlayerRoleList(Integer accountId); //获取用户的所有角色列表
    /***
     * @author echo.ch
     * @param accountId
     * @return
     */
    Integer isOnline(Integer accountId);//查询角色是否登陆
    
    /**
     * 
     */
    PlayerRole getPlayerRole(Integer id);
    
    PlayerRole getPlayerRole(String playerName);
    
    int updateRoleByName(PlayerRole record );
    
    int updateRoleById(PlayerRole record);
}