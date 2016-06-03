package org.gameprototype.cache.player;

import org.gameprototype.dao.model.PlayerEquip;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/9/17.
 */
public interface IPlayerEquipCache {
    int insert(Integer playerRoleId, Integer equipId);
    int delete(Integer playerEquipId, Integer playerRoleId, Integer equipId);
    int updateStatus(Integer status, Integer id); //更新设备状态
    PlayerEquip get(Integer id); //获取玩家-装备信息
    List<PlayerEquip> getList(Integer playerRoleId); //获取用户角色的装备列表
}
