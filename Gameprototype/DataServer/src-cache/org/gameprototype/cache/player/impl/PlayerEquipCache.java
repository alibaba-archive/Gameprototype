package org.gameprototype.cache.player.impl;

import org.gameprototype.base.redis.KeysTools;
import org.gameprototype.base.redis.RedisTools;
import org.gameprototype.cache.player.IPlayerEquipCache;
import org.gameprototype.dao.IPlayerEquipDAO;
import org.gameprototype.dao.model.PlayerEquip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haihong.xiahh on 2015/9/17.
 */
@Service("playerEquipCache")
public class PlayerEquipCache implements IPlayerEquipCache{
    @Autowired
    private RedisTools redisTools;

    @Autowired
    private IPlayerEquipDAO playerEquipDAO;

    private String getPlayerEquipIDKey(Integer playerEquipID) {
        return KeysTools.PlayerKeys.playerEquipIDKey(playerEquipID);
    }

    private String getPlayerEquipListKey(Integer playerRoleID) {
        return KeysTools.PlayerKeys.playerEquipListKey(playerRoleID);
    }


    @Override
    public int insert(Integer playerRoleId, Integer equipId) {
        PlayerEquip playerEquip = new PlayerEquip();
        playerEquip.setPlayerId(playerRoleId);
        playerEquip.setEquipId(equipId);
        int ret = playerEquipDAO.insert(playerEquip);
        if (ret > 0) {
            redisTools.OBJECTS.set(this.getPlayerEquipIDKey(playerEquip.getId()), playerEquip);
        }
        return 0;
    }

    @Override
    public int delete(Integer playerEquipId, Integer playerRoleId, Integer equipId) {
        PlayerEquip playerEquip = new PlayerEquip();
        playerEquip.setId(playerEquipId);
        playerEquip.setPlayerId(playerRoleId);
        playerEquip.setEquipId(equipId);
        int ret = playerEquipDAO.delete(playerEquip);
        if (ret > 0) {
            redisTools.KEYS.del(this.getPlayerEquipIDKey(playerEquip.getId()));
        }
        return ret;
    }

    @Override
    public int updateStatus(Integer status, Integer id) {
        int ret = playerEquipDAO.updateStatus(status, id);
        if (ret > 0) {
            redisTools.KEYS.del(this.getPlayerEquipIDKey(id));
        }
        return ret;
    }

    @Override
    public PlayerEquip get(Integer id) {
        String key = this.getPlayerEquipIDKey(id);
        Object object = redisTools.OBJECTS.get(key);
        if (object == null) {
            PlayerEquip playerEquip = playerEquipDAO.get(id);
            if (playerEquip != null) {
                redisTools.OBJECTS.set(key, playerEquip);
                return playerEquip;
            } else {
                return null;
            }
        } else {
            return (PlayerEquip) object;
        }
    }

    @Override
    public List<PlayerEquip> getList(Integer playerRoleId) {
        String key = this.getPlayerEquipListKey(playerRoleId);
        Object object = redisTools.OBJECTS.get(key);
        if (object == null) {
            List<PlayerEquip> playerEquipList = playerEquipDAO.getList(playerRoleId);
            if (playerEquipList != null) {
                redisTools.OBJECTS.set(key, playerEquipList);
                return playerEquipList;
            } else {
                return null;
            }
        } else {
            return (List<PlayerEquip>) object;
        }
    }
}
