package org.gameprototype.dao.model;

/**
 * Created by haihong.xiahh on 2015/10/8.
 */
public class PlayerStatus {
    public static final int OFFLINE = 1000; // 离线
    public static final int ONLINE_SINGLE = 1001; // 在线未组队
    public static final int ONLINE_TEAM = 2001; // 在线已组队
    public static final int BATTLE_SINGLE = 1003; // 单独战斗中
    public static final int BATTLE_TEAM = 2003; // 组队战斗中

    public static final int PLAYER_DEAD = 3000; // 玩家战死
    public static final int PLAYER_WEAK = 3001; // 玩家虚弱（血量低于30%）
    public static final int PLAYER_HEALTH = 3002; // 玩家正常（血量高于30%）
    public static final int PLAYER_STRONG = 3003; // 玩家健康（满血状态）
}
