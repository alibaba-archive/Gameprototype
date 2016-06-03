package org.gameprototype;

import org.gameprototype.dao.model.PlayerEquip;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/10/19.
 */
public interface IPlayerEquipBiz {
    List<PlayerEquip> getList(Integer playerRoleId);
    PlayerEquip getDetail(Integer playerEquipId);
    int insert(Integer playerRoleId, Integer equipId);
    int delete(Integer playerEquipId, Integer playerRoleId, Integer equipId);
}
