package org.gameprototype.cache.combat;


import java.util.List;

import org.gameprototype.dao.model.Combat;
import org.gameprototype.dao.model.MonsterRole;
import org.gameprototype.dao.model.PlayerRole;
import org.gameprototype.dao.model.PlayerStatus;


/**
 * Created by zhuowu.zm on 2015/9/17.
 */
public interface ICombatCache {

    /**
     * 获取对应的战斗信息
     * @param playerInfo 玩家信息，List类型
     * @param monsterInfo 怪物信息
     * @param MapID 地图对应编号
     * @return
     * @throws Exception
     */
    //Combat getCombatInfo(List<PlayerRole> playerInfo, MonsterRole monsterInfo, Integer MapID) throws Exception;

    /**
     * 返回战役中的玩家列表
     * @throws Exception
     */
    //List<PlayerRole> getCombatPlayerInfo(String combatId) throws Exception;

    /**
     * 返回战役中怪物信息
     * 规定任何打怪中都只有一个怪物(TBD)
     */
    //MonsterRole getCombatMonsterInfo(String combatId) throws Exception;

    /**
     * 根据战斗ID返回战斗结果
     * @param combatId
     * @return
     * @throws Exception
     */
    //List<PlayerRole> getCombatResult(String combatId) throws Exception;

    /**
     * 战斗结束调用，根据传回服务器的PlayerRole信息更新玩家信息
     * @param playerRoleInfo 客户端传入的（单个）玩家信息
     * @return
     * @throws Exception
     */
    Integer combatEnd(PlayerRole playerRoleInfo) throws Exception;

    /**
     * 战斗结束调用，根据传入experience、damage、hitpoints更新玩家信息
     * @param PlayerRoleId 玩家对应Id
     * @param experience   经验值
     * @param damage       攻击力
     * @param hitpoints    伤害值
     * @param defense      防御力
     * @return
     */
    PlayerRole combatEnd(Integer playerRoleId, Integer playerStatus, Integer level, Integer hitPoints, Integer curHitPoints, Integer experience, Integer damage, Integer defense);
}
