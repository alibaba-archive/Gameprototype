package org.gameprototype.impl;

import java.util.List;

import org.gameprototype.IGameMapBiz;
import org.gameprototype.cache.map.IMapCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("gameMapBiz")
public class GameMapBiz implements IGameMapBiz {

	@Autowired
	private IMapCache mapCache;

	@Override
	public List<Integer> getMapList() {
		// TODO Auto-generated method stub
		return mapCache.getMapList();
	}

	@Override
	public Long updateMapRole(Integer mapid, Integer roleid) {
		// TODO Auto-generated method stub
		return mapCache.updateMapRole(mapid, roleid);
	}

	@Override
	public Long deleteMapRole(Integer mapid, Integer roleid) {
		// TODO Auto-generated method stub
		return mapCache.deleteMapRole(mapid, roleid);
	}

	@Override
	public List<Integer> getMapRoleList(Integer mapid) {
		// TODO Auto-generated method stub
		return mapCache.getMapList();
	}

}
