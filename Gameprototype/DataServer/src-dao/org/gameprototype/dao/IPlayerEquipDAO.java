package org.gameprototype.dao;

import org.gameprototype.dao.model.PlayerEquip;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/9/16.
 */
public interface IPlayerEquipDAO {
    int insert(PlayerEquip playerEquip); //用户角色增加装备
    int delete(PlayerEquip playerEquip); //用户角色删除装备
    int updateStatus(Integer status, Integer id); //更新设备状态
    PlayerEquip get(Integer id); //获取玩家-装备信息
    List<PlayerEquip> getList(Integer playerRoleId); //获取用户角色的装备列表
}
