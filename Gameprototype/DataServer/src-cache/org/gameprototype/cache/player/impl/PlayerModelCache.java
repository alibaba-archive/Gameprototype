package org.gameprototype.cache.player.impl;

import org.gameprototype.base.redis.KeysTools;
import org.gameprototype.base.redis.RedisTools;
import org.gameprototype.cache.player.IPlayerModelCache;
import org.gameprototype.dao.IPlayerModelDAO;
import org.gameprototype.dao.model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/9/17.
 */
@Service("playerModelCache")
public class PlayerModelCache implements IPlayerModelCache{
    @Autowired
    private RedisTools redisTools;

    @Autowired
    private IPlayerModelDAO playerModelDAO;

    private String getPlayerModelIDKey(Integer playerModelID) {
        return KeysTools.PlayerKeys.playerModelIDKey(playerModelID);
    }

    private String getPlayerModelListKey() {
        return KeysTools.PlayerKeys.playerModelListKey();
    }

    @Override
    public int insert(PlayerModel playerModel) {
        int ret = playerModelDAO.insert(playerModel);
        if (ret > 0) {
            redisTools.OBJECTS.set(this.getPlayerModelIDKey(playerModel.getId()), playerModel);
        }
        return ret;
    }

    @Override
    public int delete(Integer playerModelId) {
        int ret = playerModelDAO.delete(playerModelId);
        if (ret > 0) {
            redisTools.KEYS.del(this.getPlayerModelIDKey(playerModelId));
        }
        return ret;
    }

    @Override
    public int update(PlayerModel playerModel) {
        int ret = playerModelDAO.update(playerModel);
        if (ret > 0) {
            redisTools.KEYS.del(this.getPlayerModelIDKey(playerModel.getId()));
        }
        return ret;
    }

    @Override
    public PlayerModel get(Integer playerModelId) {
        String key = this.getPlayerModelIDKey(playerModelId);
        Object object = redisTools.OBJECTS.get(key);
        if (object == null) {
            PlayerModel playerModel = playerModelDAO.get(playerModelId);
            if (playerModel != null) {
                redisTools.OBJECTS.set(key, playerModel);
                return playerModel;
            } else {
                return null;
            }
        } else {
            return (PlayerModel) object;
        }
    }

    @Override
    public List<PlayerModel> getList() {
        String key = this.getPlayerModelListKey();
        Object object = redisTools.OBJECTS.get(key);
        if (object == null) {
            List<PlayerModel> playerModelList = playerModelDAO.getList();
            if (playerModelList != null) {
                redisTools.OBJECTS.set(key, playerModelList);
                return playerModelList;
            } else {
                return null;
            }
        } else {
            return (List<PlayerModel>) object;
        }
    }
}
