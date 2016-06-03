package org.gameprototype.logic.player.protocol;

/**
 * User : zhoubo
 * DATE : 2015/10/14
 * TIME : 15:15
 * 装备区域码
 */
public class EquiProtocol {

    public static final int GET_LIST_CREQ = 0x1; //获取装备列表
    public static final int GET_LIST_SRES = 0x2; //返回装备列表
    public static final int GET_DETAIL_CREQ = 0x3; //获取装备详情
    public static final int GET_DETAIL_SRES = 0x4; //返回装备详情
    public static final int INSERT_EQUIP_CREQ = 0x5; //增加装备请求
    public static final int INSERT_EQUIP_SRES = 0x6; //返回增加装备结果
    public static final int DELETE_EQUIP_CREQ = 0x7; //增加装备请求
    public static final int DELETE_EQUIP_SRES = 0x8; //返回增加装备结果

}
