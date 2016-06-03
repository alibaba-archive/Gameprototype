package org.gameprototype.dao;

import org.gameprototype.dao.model.GameMap;
import org.springframework.stereotype.Service;

import com.sun.tools.javac.util.List;

import java.lang.Integer;

/**
 * Created by chao.zhangch on 2015/10/20.
 */
public interface IMapDAO {
    /**
     * 获取地图信息
     */
    GameMap getMapDetailById(Integer id);
	List<Integer> getMapList();
}
