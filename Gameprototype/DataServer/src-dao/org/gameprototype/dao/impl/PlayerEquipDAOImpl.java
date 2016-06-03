package org.gameprototype.dao.impl;

import org.gameprototype.dao.IPlayerEquipDAO;
import org.gameprototype.dao.mapper.PlayerEquipMapper;
import org.gameprototype.dao.model.PlayerEquip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/9/16.
 */
@Service("playerEquipDAO")
public class PlayerEquipDAOImpl implements IPlayerEquipDAO {
    @Autowired
    PlayerEquipMapper playerEquipMapper;

    @Override
    public int insert(PlayerEquip playerEquip) {
        return playerEquipMapper.insert(playerEquip);
    }

    @Override
    public int delete(PlayerEquip playerEquip) {
        return playerEquipMapper.deleteByPlayerEquip(playerEquip);
    }

    @Override
    public int updateStatus(Integer status, Integer id) {
        PlayerEquip playerEquip = new PlayerEquip();
        playerEquip.setId(id);
        playerEquip.setStatus(status);
        return playerEquipMapper.updateByPrimaryKeySelective(playerEquip);
    }

    @Override
    public PlayerEquip get(Integer id) {
        return playerEquipMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PlayerEquip> getList(Integer playerRoleId) {
        return playerEquipMapper.selectByPlayerId(playerRoleId);
    }
}
