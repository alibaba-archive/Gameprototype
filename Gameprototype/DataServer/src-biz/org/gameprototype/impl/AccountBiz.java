package org.gameprototype.impl;

import io.netty.channel.Channel;
import org.gameprototype.IAccountBiz;
import org.gameprototype.cache.account.IAccountCache;
import org.gameprototype.dao.model.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhoubo on 15-9-6.
 */

@Service("accountBiz")

public class AccountBiz implements IAccountBiz {


	@Resource(name="accountCache")

    private IAccountCache accountCache ;

    @Override
    public int login(Channel channel, String account, String passWord) {

        //通过唯一ID 获取spring中管理的帐号缓存对象
        

        //有没有此帐号存在
        if(!accountCache.hasAccount(account)){
            return -1;//返回-1表示帐号不存在
        }
        if(accountCache.isOnLine(account)){
            return -3;//返回-3表示帐号当前处于登录状态
        }
        Account accountModel= accountCache.get(account);
        if(!accountModel.getPassWord().equals(passWord)){
            return -2;//返回-2表示密码错误
        }
        //写入帐号上线缓存
        accountCache.onLine(channel, account);
        //登录成功 返回帐号ID
        return accountModel.getId();
    }

    @Override
    public int create(String account, String passWord) {
        if(accountCache.hasAccount(account)){
            return -4;//返回表示帐号存在
        }
        if(account.isEmpty() || account.length()>16 ||account.length()<4){
            return -5;//帐号不合法
        }
        if(passWord.isEmpty() || passWord.length()>16 ||passWord.length()<4){
            return -6;//密码不合法
        }
        accountCache.create(account, passWord);
        return 0;//返回0 表示注册成功
    }

    @Override
    public void offline(Channel channel) {
        accountCache.offLine(channel);
    }

	
    
    

}
