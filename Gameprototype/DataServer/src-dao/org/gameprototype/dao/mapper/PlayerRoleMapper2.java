package org.gameprototype.dao.mapper;

import java.util.List;

import org.gameprototype.dao.model.Account;
import org.gameprototype.dao.model.PlayerRole;

public interface PlayerRoleMapper2 {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player_role
     *
     * @mbggenerated Mon Sep 14 15:39:59 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player_role
     *
     * @mbggenerated Mon Sep 14 15:39:59 CST 2015
     */
    int insert(PlayerRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player_role
     *
     * @mbggenerated Mon Sep 14 15:39:59 CST 2015
     */
    int insertSelective(PlayerRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player_role
     *
     * @mbggenerated Mon Sep 14 15:39:59 CST 2015
     */
    PlayerRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player_role
     *
     * @mbggenerated Mon Sep 14 15:39:59 CST 2015
     */
    int updateByPrimaryKeySelective(PlayerRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player_role
     *
     * @mbggenerated Mon Sep 14 15:39:59 CST 2015
     */
    int updateByPrimaryKey(PlayerRole record);
    
    int isOnline(Integer accountID);
    
    List<PlayerRole> selectList(PlayerRole account);
    
    int updatePlayerRoleHitpoints(PlayerRole record);
    
    PlayerRole selectPlayerRoleByName(String name);
    
    int updateRoleByName(PlayerRole account);
    
    
    

}