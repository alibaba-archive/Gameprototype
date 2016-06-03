package org.gameprototype.cache.combat.impl;

import org.gameprototype.cache.combat.ICombatCache;
import org.gameprototype.cache.player.impl.PlayerRoleCache;
import org.gameprototype.dao.impl.PlayerRoleDAOImpl;
import org.gameprototype.dao.model.PlayerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhuowu.zm on 2015/10/20.
 */

@Service("combatCache")
public class CombatCache implements ICombatCache {

    @Autowired
    private PlayerRoleCache playerRoleCache;

    @Autowired
    private PlayerRoleDAOImpl playerRoleDAO;

    /**
    @Override
    public Combat getCombatInfo(List<PlayerRole> playerInfo, MonsterRole monsterInfo, Integer MapCode) {

        // 先生成combatId
        combat.setCombatID("Combat_ID_test_000001"); // 后续更新

        // 处理Player信息
        Integer playerDamage;
        Integer playerDefense;
        Integer playerHitPoints;

        for(int i = 0; i < playerInfo.size(); i++) {
            // 根据列表获取单个用户的伤害、防御、
            PlayerRole playerRole = playerInfo.get(i);
            playerDamage = playerRole.getDamage();
            playerDefense = playerRole.getDefense();
            playerHitPoints = playerRole.getHitpoints();
        }

        // 处理Monster信息
        Integer monsterDamage = monsterInfo.getDamage();
        Integer monsterDefense = monsterInfo.getDefense();
        Integer monsterHitPoints = monsterInfo.getHitpoints();
    }
    */

    @Override
    public Integer combatEnd(PlayerRole playerRoleInfo) {

        // 更新用户信息
        return playerRoleDAO.updateRoleById(playerRoleInfo);
    }

    @Override
    public PlayerRole combatEnd(Integer playerRoleId, Integer playerStatus, Integer level, Integer hitPoints, Integer curHitPoints, Integer experience, Integer damage, Integer defense) {

        // 根据playerRoleId获取对应用户
        PlayerRole playerRole = playerRoleCache.getPlayerRoleById(playerRoleId);

        // 根据客户端传入参数更新用户信息
        updatePlayerAttribute(playerRole, playerStatus, level, hitPoints, curHitPoints, experience, damage, defense);

        return playerRole;

    }

    private void updatePlayerAttribute(PlayerRole player, Integer playerStatus, Integer level, Integer hitPoints, Integer curHitPoints, Integer experience, Integer defense, Integer damage) {

        player.setStatus(playerStatus);           // 更新玩家状态
        player.setLevel(level);                   // 设置等级
        player.setHitpoints(hitPoints);           // 设置总血量
        player.setCurrentHitpoints(curHitPoints); // 更新当前血量
        player.setExperience(experience);         // 设置经验值
        player.setDefense(defense);               // 设置防御力
        player.setDamage(damage);                 // 设置伤害值

    }
}
