package org.gameprototype.dao.impl;

import org.gameprototype.dao.IMapDAO;
import org.gameprototype.dao.mapper.GameMapMapper;
import org.gameprototype.dao.model.GameMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.tools.javac.util.List;

@Service("gameMapDAO")
public class MapDAOImpl implements IMapDAO {

    @Autowired
    private GameMapMapper gameMapMapper;
    
    @Override
    public GameMap getMapDetailById(Integer id) {
        // TODO Auto-generated method stub
       return gameMapMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Integer> getMapList() {
        // TODO Auto-generated method stub
        return gameMapMapper.selectMapList();
    }

}
