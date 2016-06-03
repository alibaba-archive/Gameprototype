package org.gameprototype.test;

import org.gameprototype.dao.IAccountDAO;
import org.gameprototype.dao.model.Account;
import org.gameprototype.datasource.DbContextHolder;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by haihong.xiahh on 2015/9/16.
 */
public class AccountDAOTest extends SpringJunitTest {
    @Resource
    private IAccountDAO accountDAO;

    @Test
    public void testInsertAccount() {
//    	DbContextHolder.setDbType("ds_w");
        Account account = new Account();
        account.setAccount("echo.ch1233412");
        account.setPassWord("12346j1jj");
        int ret = accountDAO.save(account);
        Assert.assertEquals("insert account", 1, ret);
    }

}
