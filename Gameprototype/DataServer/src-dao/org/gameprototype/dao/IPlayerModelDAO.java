package org.gameprototype.dao;

import org.gameprototype.dao.model.*;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/9/7.
 */
public interface IPlayerModelDAO {
    int insert(PlayerModel playerModel);//增加角色信息
    int delete(Integer roleInfoId);//删除角色信息
    int update(PlayerModel playerModel);//更新角色信息
    PlayerModel get(Integer roleInfoId);//获取角色信息
    List<PlayerModel> getList();//获取所有角色列表
}
