package org.gameprototype.tool.impl;

public class GdCodeUtil {
	/**
	 * 武将
	 * */
	public static final int ARMY=0;
	/**
	 * NPC
	 * */
	public static final int NPC=1;
	/**
	 * UI
	 * */
	public static final int UI=2;
	/**
	 * 怪物
	 * */
	public static final int MONSTER=3;
	/**
	 * 装备
	 * */
	public static final int EQUIPMENT=4;
	/**
	 * 道具
	 * */
	public static final int ITEM=5;
	/**
	 * 技能
	 * */
	public static final int SKILL=6;
	/**
	 * 未知（即GDCODE错误 没有在定义段内）
	 * */
	public static final int UNKNOWN=7; 
	/**
	 * 经验
	 * */
	public static final int EXP=8; 
	
	/**
	 * 游戏币
	 * */
	public static final int MONEY=9; 
	
	/**
	 * 元宝
	 * */
	public static final int GOLD=10; 
	/**
	 * 潜能
	 * */
	public static final int PP=11; 
	
	
	public static final int MONEY_CODE=359801; 
	public static final int GOLD_CODE=359802; 

	public static int type(int gdCode) {
		if (gdCode < 30000) {
			return ARMY;
		}
		if (gdCode < 50000) {
			return NPC;
		}
		if (gdCode < 150000) {
			return UI;
		}
		if (gdCode < 200000) {
			return MONSTER;
		}
		if (gdCode < 260000) {
			return EQUIPMENT;
		}
		if (gdCode < 360000) {
			if(gdCode==359801){
				return MONEY;
			}
			if(gdCode==359802){
				return GOLD;
			}
			if(gdCode==359803 ){
				return PP;
			}
			if(gdCode==359804 ){
				return EXP;
			}
			return ITEM;
		}
		if (gdCode < 400000) {
			return SKILL;
		}
		return UNKNOWN;
	}
}
