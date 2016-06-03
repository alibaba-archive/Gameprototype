package org.gameprototype.combat.impl;

import org.gameprototype.combat.ICombatBiz;
import org.gameprototype.dao.model.MonsterRole;
import org.gameprototype.dao.model.PlayerRole;


/**
 * Created by zhuowu.zm on 2015/9/16.
 */
public class CombatBiz implements ICombatBiz {


    @Override
    public void startCombat(PlayerRole playerRole, MonsterRole monsterRole) {
        // 进入战斗
    }

    @Override
    public void stopCombat(PlayerRole playerRole) {
        // 退出战斗
    }

    @Override
    public int combatResult(PlayerRole playerRole, MonsterRole monsterRole) {
        // 返回战斗结果

        return -1;
    }

    @Override
    public PlayerRole updatePlayerStatus(PlayerRole playerRole) {
        // 更新用户状态
        PlayerRole pr = null;
        return pr;
    }

    @Override
    public MonsterRole updateMonsterStatus(MonsterRole monsterRole) {
        // 更新怪物状态
        MonsterRole mr = null;
        return mr;
    }


}
