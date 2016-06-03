package org.gameprototype.dao.impl;

import org.gameprototype.dao.IPlayerTeamDAO;
import org.gameprototype.dao.mapper.PlayerTeamMapper;
import org.gameprototype.dao.model.PlayerTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by haihong.xiahh on 2015/9/21.
 */
@Service("playerTeamDAO")
public class PlayerTeamDAOImpl implements IPlayerTeamDAO {
    @Autowired
    private PlayerTeamMapper playerTeamMapper;

    @Override
    public int insertPlayerTeam(PlayerTeam playerTeam) {
        return playerTeamMapper.insert(playerTeam);
    }

    @Override
    public int updatePlayerTeam(PlayerTeam playerTeam) {
        return playerTeamMapper.updateByPrimaryKeySelective(playerTeam);
    }
}
