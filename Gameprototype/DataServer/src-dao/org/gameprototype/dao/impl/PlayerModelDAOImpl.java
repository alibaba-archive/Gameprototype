package org.gameprototype.dao.impl;

import org.gameprototype.dao.IPlayerModelDAO;
import org.gameprototype.dao.mapper.PlayerModelMapper;
import org.gameprototype.dao.model.PlayerEquip;
import org.gameprototype.dao.model.PlayerModel;
import org.gameprototype.dao.model.PlayerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/9/7.
 */
@Service("playerModelDAO")
public class PlayerModelDAOImpl implements IPlayerModelDAO {

    @Autowired
    PlayerModelMapper playerModelMapper;

    /**
     *
     * @param playerModel
     * @return id after insert
     */
    @Override
    public int insert(PlayerModel playerModel) {
        return playerModelMapper.insert(playerModel);
    }

    @Override
    public int delete(Integer roleInfoId) {
        return playerModelMapper.deleteByPrimaryKey(roleInfoId);
    }

    @Override
    public int update(PlayerModel playerModel) {
        return playerModelMapper.updateByPrimaryKeySelective(playerModel);
    }

    @Override
    public PlayerModel get(Integer roleInfoId) {
        return playerModelMapper.selectByPrimaryKey(roleInfoId);
    }

    @Override
    public List<PlayerModel> getList() {
        return playerModelMapper.selectAll();
    }

}
