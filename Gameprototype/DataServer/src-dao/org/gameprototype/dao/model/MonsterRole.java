package org.gameprototype.dao.model;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by zhuowu.zm on 2015/9/16.
 */
public class MonsterRole implements Serializable {

    /**
     * 怪物ID
     */
    private Integer monsterID;

    /**
     * 怪物名称
     */
    private String monsterName;

    /**
     * 怪物描述
     */
    private String monsterDesc;

    /**
     * 怪物生命值
     */
    private Integer hitpoints;

    /**
     * 怪物攻击力
     */
    private Integer damage;

    /**
     * 怪物防御力
     */
    private Integer defense;

    /**
     * 怪物等级
     */
    private Integer level;

    /**
     * 怪物出现的地图ID
     */
    private Integer mapID;

    /**
     * 怪物在地图上的坐标
     */
    private Point coordinate;

    public Integer getMonsterID() {
        return monsterID;
    }

    public void setMonsterId(Integer monsterID) {
        this.monsterID = monsterID;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public String getMonsterDesc() {
        return monsterDesc;
    }

    public void setMonsterDesc(String monsterDesc) {
        this.monsterDesc = monsterDesc;
    }

    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMapID() {
        return mapID;
    }

    public void setMapID(Integer mapID) {
        this.mapID = mapID;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }
}
