package org.gameprototype.impl;

import org.gameprototype.IPlayerEquipBiz;
import org.gameprototype.cache.player.IPlayerEquipCache;
import org.gameprototype.dao.model.PlayerEquip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/10/19.
 */
@Service("playerEquipBiz")
public class PlayerEquipBiz implements IPlayerEquipBiz {
    @Autowired
    IPlayerEquipCache playerEquipCache;

    @Override
    public List<PlayerEquip> getList(Integer playerRoleId) {
        return playerEquipCache.getList(playerRoleId);
    }

    @Override
    public PlayerEquip getDetail(Integer playerEquipId) {
        return playerEquipCache.get(playerEquipId);
    }

    @Override
    public int insert(Integer playerRoleId, Integer equipId) {
        return playerEquipCache.insert(playerRoleId, equipId);
    }

    @Override
    public int delete(Integer playerEquipId, Integer playerRoleId, Integer equipId) {
        return playerEquipCache.delete(playerEquipId, playerRoleId, equipId);
    }
}
