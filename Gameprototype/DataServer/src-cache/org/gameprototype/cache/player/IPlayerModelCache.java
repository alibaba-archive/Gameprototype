package org.gameprototype.cache.player;

import org.gameprototype.dao.model.PlayerModel;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/9/17.
 */
public interface IPlayerModelCache {
    int insert(PlayerModel playerModel);//增加角色信息
    int delete(Integer playerModelId);//删除角色信息
    int update(PlayerModel playerModel);//更新角色信息
    PlayerModel get(Integer playerModelId);//获取角色信息
    List<PlayerModel> getList();//获取所有角色列表

}
