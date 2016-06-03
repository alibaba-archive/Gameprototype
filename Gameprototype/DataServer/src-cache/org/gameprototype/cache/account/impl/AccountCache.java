package org.gameprototype.cache.account.impl;

import io.netty.channel.Channel;
import org.gameprototype.cache.account.IAccountCache;
import org.gameprototype.dao.IAccountDAO;
import org.gameprototype.dao.model.Account;
import org.gameprototype.dao.model.PlayerModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User : zhoubo
 * DATE : 2015/10/14
 * TIME : 17:20
 */
/**
 * 
 * @author echo.ch
 *增加相关实现类 将缓存改为redis
 */
@Service("accountCache")
public class AccountCache implements IAccountCache{

    //通过 spring管理的唯一ID获取实例对象
    @Resource(name="accountDAO")
    private IAccountDAO accountDAO;

    private Map<String, Account> accountMap=new HashMap<String, Account>();
    private Map<String, Channel> onLineMapAsAccount=new HashMap<String, Channel>();
    private Map<Channel,String > onLineMapAsChannel=new HashMap<Channel,String>();
    @Override
    public boolean hasAccount(String account) {
        initCache(account);
        return accountMap.containsKey(account);
    }



    @Override
    public Account get(String account) {
        initCache(account);
        return accountMap.get(account);
    }

    private void initCache(String account){
        //缓存中有此帐号数据，不用再次初始化，跳出方法
        if(accountMap.containsKey(account))return;
        //从数据层获取帐号
        Account accountModel= accountDAO.get(account);
        if(accountModel!=null){
            //帐号存在，写入缓存
            accountMap.put(account, accountModel);
        }
    }

    @Override
    public boolean isOnLine(String account) {
        return onLineMapAsAccount.containsKey(account);
    }

    @Override
    public void onLine(Channel channel, String account) {
        //写入帐号和连接的映射
        onLineMapAsAccount.put(account, channel);
        //反向--写入连接和帐号的映射
        onLineMapAsChannel.put(channel, account);
    }

    @Override
    public void offLine(String account) {
        //判断是否有帐号在线信息
        if(onLineMapAsAccount.containsKey(account)){
            //通过帐号连接映射 获取连接  移除连接映射的内容
            onLineMapAsChannel.remove(onLineMapAsAccount.get(account));
            //移除帐号连接映射
            onLineMapAsAccount.remove(account);
        }
    }

    @Override
    public void offLine(Channel channel) {
        //判断是否有连接在线信息
        if(onLineMapAsChannel.containsKey(channel)){
            //与帐号下线 反向思维
            onLineMapAsAccount.remove(onLineMapAsChannel.get(channel));
            onLineMapAsChannel.remove(channel);
        }
    }

    @Override
    public void create(String account, String passWord) {
        Account accountModel=new Account();
        accountModel.setAccount(account);
        accountModel.setPassWord(passWord);
        accountDAO.save(accountModel);
    }




    
    


}
