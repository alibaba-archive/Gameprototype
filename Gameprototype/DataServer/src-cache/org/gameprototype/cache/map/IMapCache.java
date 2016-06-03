package org.gameprototype.cache.map;

import org.gameprototype.dao.model.GameMap;
import org.springframework.stereotype.Service;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.lang.Integer;
import java.util.List;

/**
 * Created by chao.zhangch on 2015/10/20.
 */
public interface IMapCache {
    /**
     * 获取地图信息
     */
	GameMap getMapDetailById(Integer id);

	List<Integer> getMapList();

	Long updateMapRole(Integer mapid, Integer roleid);

	Long deleteMapRole(Integer mapid, Integer roleid);

	List<Integer> getMapRoleList(Integer mapid);
}
;