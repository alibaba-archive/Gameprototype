package org.gameprototype.dao;

import org.gameprototype.dao.model.Account;

/**
 * Created by zhoubo on 15-9-6.
 */
public interface IAccountDAO {

    /**
     * save pojo
     */ 
	int save(Account account);

    /**
     * update pojo
     */
    int update(Account account);

    /**
     * get pojo
     */
    Account get(String account);

    /**
     * remove
     */
    int remove(Integer id);
}
