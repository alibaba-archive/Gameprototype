package org.gameprototype;


import io.netty.channel.Channel;

/**
 * Created by zhoubo on 15-9-6.
 */
public interface IAccountBiz {

    /**
     * 登录
     * @return 登录结果  大于0 表示登录成功 返回用户ID
     * 小于0表示登录失败 数值为失败原因
     * */
    int login(Channel channel, String account,String passWord);

    /**
     * 帐号创建
     * */
    int create(String account,String passWord);

    /**
     * 帐号离线
     * @param channel
     */
    void offline(Channel channel);
    
    
}
