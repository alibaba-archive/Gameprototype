package org.gameprototype.tool.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class DateUtil {
	
	static final int REFRESH_TIME=6;
	
	/**
	 * 是否需要每日更新 即每天早上六点之前无法更新 当前时间在六点之后历史时间在六点之前则可以更新(此方法不关注流逝天数 如需根据天数计算 请调用lastDay方法)
	 * @return true 需要刷新 false不需要刷新
	 * */
	public static boolean isRefresh(Date t1, Date t2) {
		Calendar cd1=Calendar.getInstance();
		cd1.setTime(t1);
		Calendar cd2=Calendar.getInstance();
		cd2.setTime(t2);
		if (DateUtils.isSameDay(t1, t2)) {
			// t1大于t2 说明t2为历史时间 t1为当前比对时间 否则反之			
			return sameDay(cd1, cd2);
		}else{
			if (cd1.getTimeInMillis() > cd2.getTimeInMillis()){
				if (cd1.get(Calendar.HOUR_OF_DAY) > REFRESH_TIME) {
					return true;
				}
			}else{
				if (cd2.get(Calendar.HOUR_OF_DAY) > REFRESH_TIME) {
						return true;					
				} 
			}
			return false;
		}
	}
	/**
	 * 同一天内 是否需要更新
	 * */
	public static boolean sameDay(Calendar cd1, Calendar cd2){
		if (cd1.getTimeInMillis() > cd2.getTimeInMillis()) {
			if (cd1.get(Calendar.HOUR_OF_DAY) > REFRESH_TIME) {
				if(cd2.get(Calendar.HOUR_OF_DAY)<REFRESH_TIME){
					return true;
				}
				return false;
			} else {
				return false;
			}
		} else {
			if (cd2.get(Calendar.HOUR_OF_DAY) > REFRESH_TIME) {
				if(cd1.get(Calendar.HOUR_OF_DAY)<REFRESH_TIME){
					return true;
				}
				return false;
			} else {
				return false;
			}
		}
	}
	
	public static  boolean isSameMonth(Date t1, Date t2){
			// t1大于t2 说明t2为历史时间 t1为当前比对时间 否则反之
			Calendar cd1=Calendar.getInstance();
			cd1.setTime(t1);
			Calendar cd2=Calendar.getInstance();
			cd2.setTime(t2);
			return isSameMonth(cd1, cd2);
	}
	
	public static  boolean isSameMonth(Calendar cd1, Calendar cd2){
		if(cd1.get(Calendar.MONTH)==cd2.get(Calendar.MONTH)&&cd1.get(Calendar.YEAR)==cd2.get(Calendar.YEAR)){
			return true;
		}
		return false;
	}
	
	
	public static long getNow(){
		Calendar cd=Calendar.getInstance();
		return cd.getTimeInMillis();
	}
	/**
	 * 获取当天的指定时间
	 * */
	public static long getTime(int h,int m,int s){
		Calendar cd=Calendar.getInstance();
		cd.set(Calendar.HOUR_OF_DAY, h);
		cd.set(Calendar.MINUTE, m);
		cd.set(Calendar.SECOND, s);
		return cd.getTimeInMillis();
	}
	/**
	 * 获取指定时间
	 * */
	public static long getTime(int y,int M,int d,int h,int m,int s){
		Calendar cd=Calendar.getInstance();
		cd.set(Calendar.YEAR, y);
		cd.set(Calendar.MONTH, M);
		cd.set(Calendar.DAY_OF_MONTH, d);
		cd.set(Calendar.HOUR_OF_DAY, h);
		cd.set(Calendar.MINUTE, m);
		cd.set(Calendar.SECOND, s);
		return cd.getTimeInMillis();
	}
	/**
	 * 获取明天的指定时间
	 * */
	public static long getNextDayTime(int h,int m,int s){
		Calendar cd=Calendar.getInstance();
		cd.add(Calendar.DAY_OF_MONTH, 1);
		cd.set(Calendar.HOUR_OF_DAY, h);
		cd.set(Calendar.MINUTE, m);
		cd.set(Calendar.SECOND, s);
		return cd.getTimeInMillis();
	}
	
	/**
	 * 基于刷新时间 获取流逝天数（即使在同一天 刷新时间前和刷新时间后 都算流逝一天）
	 * 注1： 如昨天早上5点半刷新数据  今天早上6点半获取  两者之间是流逝了一天时间 但是昨天的5点半 在刷新时间之前 所以昨天的5点半-6点之间是属于前天的 故需加上一天
	 * */
	public static int lastDayByRefresh(Date t1,Date t2){
		Calendar cd1=Calendar.getInstance();
		cd1.setTime(t1);
		Calendar cd2=Calendar.getInstance();
		cd2.setTime(t2);
		if(DateUtils.isSameDay(t1, t2)){
			if(sameDay(cd1, cd2))return 1;
			return 0;
		}else{
			int h1=cd1.get(Calendar.HOUR_OF_DAY);//获取时间的小时 用于判断是否在刷新时间之前
			int h2=cd2.get(Calendar.HOUR_OF_DAY);//获取时间的小时 用于判断是否在刷新时间之前
			int ld=lastDay(cd1, cd2);
			//判断刷新时间 和当前时间 大的一个为当前时间
			if(cd1.getTimeInMillis()>cd2.getTimeInMillis()){
				if(h2<REFRESH_TIME)ld+=1;//不在同一天的情况下 如果历史时间是刷新时间之前 则历史时间中有一天是按两天算（参考：注1）
				if(cd1.get(Calendar.HOUR_OF_DAY)<REFRESH_TIME)ld-=1;
			}else{
				if(h1<REFRESH_TIME)ld+=1;//不在同一天的情况下 如果历史时间是刷新时间之前 则历史时间中有一天是按两天算（参考：注1）
				if(cd2.get(Calendar.HOUR_OF_DAY)<REFRESH_TIME)ld-=1;
			}
			return ld;
		}
	}
	
	/**
	 * 获取两个时间之间流逝的天数（按日期计算 非流逝时间算 将两个日期的时间设为相同达到效果  这里不基于刷新时间 基于刷新时间的流逝获取 调用lastDayByRefresh）
	 * */
	public static int lastDay(Date t1,Date t2){
		Calendar cd1=Calendar.getInstance();
		cd1.setTime(t1);
		Calendar cd2=Calendar.getInstance();
		cd2.setTime(t2);
		return lastDay(cd1, cd2);
	}
	/**
	 * 获取两个时间之间流逝的天数（按日期计算 非流逝时间算 将两个日期的时间设为相同达到效果）
	 * */
	public static int lastDay(Calendar cd1,Calendar cd2){
		cd1.set(Calendar.HOUR_OF_DAY, 6);
		cd1.set(Calendar.MINUTE, 0);
		cd1.set(Calendar.SECOND, 0);
		
		cd2.set(Calendar.HOUR_OF_DAY, 6);
		cd2.set(Calendar.MINUTE, 0);
		cd2.set(Calendar.SECOND, 0);
		long t1=cd1.getTimeInMillis();
		long t2=cd2.getTimeInMillis();
		if(t1>t2){
			return (int)(t1+2000-t2)/1000/24/3600;//注，这里加2000无实际意义 为了防止时间卡点 导致计算错误而加一点点时间
		}else{
			return (int)(t2+2000-t1)/1000/24/3600;
		}
	}
	
	/**
	 * 这个时间是否超过当天的刷新时间
	 * */
	public static boolean nowDayIsRefresh(Calendar cd){
		if(cd.get(Calendar.HOUR_OF_DAY)>=REFRESH_TIME)return true;
		return false;
	}
}
