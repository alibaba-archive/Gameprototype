package org.gameprototype.dao.impl;



import javax.annotation.Resource;

import org.gameprototype.dao.IAccountDAO;
import org.gameprototype.dao.mapper.AccountMapper;
import org.gameprototype.dao.model.Account;
import org.gameprototype.datasource.DbContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhoubo on 15-9-6.
 */
/**
 * add
 * @author echo.ch
 *增加相关实现方法
 */
@Service("accountDAO")
public class AccountDAOImpl implements IAccountDAO {

	@Resource
	private AccountMapper accountMapper;


	/**
	 * modify by echo.ch 将返回值改为int int值为 影响记录条数，如果返回值为小于0的值标志save操作失败
	 *
	 */
	@Override
	public int save(Account account) {
		
		int n = accountMapper.insert(account);
		if (n > 0) {
			return n;
		}
		return -1;
	}

	@Override
	public int update(Account account) {
		int i = accountMapper.updateByPrimaryKey(account);
		return i;
	}

	@Override
	public Account get(String account) {
		Account acc = accountMapper.selectByAccountName(account);
		return acc;
	}

	@Override
	public int remove(Integer id) {
		int i = accountMapper.deleteByPrimaryKey(id);
		return i;
	}
	
	
}
