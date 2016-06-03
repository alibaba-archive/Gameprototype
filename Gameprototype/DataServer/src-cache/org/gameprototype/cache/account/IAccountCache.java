package org.gameprototype.cache.account;

import io.netty.channel.Channel;

import java.util.List;

import org.gameprototype.dao.model.Account;
import org.gameprototype.dao.model.PlayerModel;

/**
 * Created by zhoubo on 15-9-6.
 */
public interface IAccountCache {

    /**
     * 有没有这个帐号  
     * */
    boolean hasAccount(String account);

    /**
     * 获取对应帐号信息
     * */
    Account get(String account);
    /**
     * 帐号是否在线
     * */
    boolean isOnLine(String account);
    /**
     * 帐号登录
     * */
    void onLine(Channel channel,String account);

    /**
     * 帐号下线
     * */
    void offLine(String account);

    /**
     * 客户端异常断开 下线
     * */
    void offLine(Channel channel);

    /**
     * 帐号注册写入
     * */
    void create(String account,String passWord);

}
