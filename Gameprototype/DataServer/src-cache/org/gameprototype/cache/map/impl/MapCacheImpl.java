package org.gameprototype.cache.map.impl;

import org.gameprototype.base.redis.KeysTools;
import org.gameprototype.base.redis.RedisTools;
import org.gameprototype.cache.map.IMapCache;
import org.gameprototype.dao.IMapDAO;
import org.gameprototype.dao.model.GameMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chao.zhangch on 2015/10/20.
 */
@Service("mapCache")
public class MapCacheImpl implements IMapCache {

	@Autowired
	private RedisTools redisTools;

	@Autowired
	private IMapDAO mapDAO;

	@Override
	public GameMap getMapDetailById(Integer id) {
		// TODO Auto-generated method stub
		String mapKey = KeysTools.MAPKEY.mapKey(id);
		Object mapObject = redisTools.OBJECTS.get(mapKey);
		if (mapObject == null) {
			GameMap gameMap = mapDAO.getMapDetailById(id);
			if (gameMap == null)
				return null;
			else {
				redisTools.OBJECTS.set(mapKey, gameMap);
				return gameMap;
			}
		}
		return (GameMap) mapObject;
	}

	@Override
	public List<Integer> getMapList() {
		// TODO Auto-generated method stub
		String mapList = KeysTools.MAPKEY.MAPLIST;
		List<String> mapListObject = redisTools.LISTS.lrange(mapList, 0, -1);
		if (mapListObject == null) {
			List<Integer> mapListObject1 = mapDAO.getMapList();
			if (mapListObject1 == null)
				return null;
			else {
				redisTools.OBJECTS.set(mapList, mapListObject1);
				return mapListObject1;
			}
		}
		List<Integer> listInts = new ArrayList<Integer>(mapListObject.size());
		for (String s : mapListObject) {
			listInts.add(Integer.valueOf(s));
		}
		return listInts;
	}

	@Override
	public Long updateMapRole(Integer mapid, Integer roleid) {
		// TODO Auto-generated method stub
		String mapKeyRole = KeysTools.MAPKEY.mapKeyRole(mapid);
		return redisTools.LISTS.lpush(mapKeyRole, roleid.toString());
	}

	@Override
	public Long deleteMapRole(Integer mapid, Integer roleid) {
		// TODO Auto-generated method stub
		String mapKeyRole = KeysTools.MAPKEY.mapKeyRole(mapid);
		return redisTools.LISTS.lrem(mapKeyRole, 1, roleid.toString());
	}

	@Override
	public List<Integer> getMapRoleList(Integer mapid) {
		// TODO Auto-generated method stub
		String mapKeyRole = KeysTools.MAPKEY.mapKeyRole(mapid);
		List<String> list = (List<String>) redisTools.LISTS.lrange(mapKeyRole, 0, -1);
		List<Integer> listInts = new ArrayList<Integer>(list.size());
		for (String s : list) {
			listInts.add(Integer.valueOf(s));
		}
		return listInts;
	}
}
