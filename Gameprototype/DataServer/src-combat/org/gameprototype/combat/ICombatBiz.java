package org.gameprototype.combat;

import org.gameprototype.dao.model.MonsterRole;
import org.gameprototype.dao.model.PlayerRole;

/**
 * Created by zhuowu.zm on 2015/9/16.
 */
public interface ICombatBiz {

    /**
     * 进入战斗
     */
    void startCombat(PlayerRole playerRole, MonsterRole monsterRole) throws Exception;

    /**
     * 退出战斗
     */
    void stopCombat(PlayerRole playerRole) throws Exception;

    /**
     * 返回战斗结果
     * 1表示胜利
     * -1表示失败
     */
    int combatResult(PlayerRole playerRole, MonsterRole monsterRole) throws Exception;


    /**
     * 更新用户状态
     * @return
     * @throws Exception
     */
    PlayerRole updatePlayerStatus(PlayerRole playerRole) throws Exception;

    /**
     * 更新怪物状态
     * @return
     * @throws Exception
     */
    MonsterRole updateMonsterStatus(MonsterRole monsterRole) throws Exception;

}
