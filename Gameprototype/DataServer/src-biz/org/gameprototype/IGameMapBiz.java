package org.gameprototype;

import java.util.List;

import org.gameprototype.dao.model.GameMap;

public interface IGameMapBiz {

	List<Integer> getMapList();

	Long updateMapRole(Integer mapid, Integer roleid);

	Long deleteMapRole(Integer mapid, Integer roleid);

	List<Integer> getMapRoleList(Integer mapid);
}
