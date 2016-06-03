package org.gameprototype.logic.player.protocol;

/**
 * User : zhoubo
 * DATE : 2015/10/14
 * TIME : 15:12
 * 角色区域码
 */

public class CharacterProtocol {
/**
 * modify by chenhong 
 * DATE ：2015/10/19
 * 增加编码
 */
	//获取场景列表
    public static final int GET_LIST_MAP = 0x1;
     //获取副本列表
    public static final int GET_LIST_SRES = 0x2;
    // 进入场景
    public static final int ENTER_MAP = 0x3;
    // 进入副本
    public static final int ENTER_SRES = 0x4;
    public static final int ENTER_BRO = 0x5;
    //获取角色列表
    public static final int GET_LIST_PLAY = 0x6;
    //得到所有的可选角色列表 
    public static final int GET_LIST_MODEl = 0x12;
    //战斗
    public static final int FIGHT_MON=0x7;
    //组队
    public static final int ADD_TEAM=0x9;
    //新建队伍
    public static final int CREATE_TEAM= 0x11;
    //离开队伍
    public static final int LEAVE_TEAM=0x10;
    //离开地图
    public static final int LEAVE_MAP=0x13;
    //得到地图内的用户列表 
    public static final  int GET_LIST_MAPROLE=0x14;
    
}
