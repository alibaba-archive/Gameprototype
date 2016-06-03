package org.gameprototype.dao.impl;


import org.gameprototype.dao.IItemDAO;
import org.gameprototype.dao.model.Shop;
import org.gameprototype.dao.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chao.zhangch on 2015/9/11.
 */

@Service("itemDAO")
public class ItemDAOImpl   implements IItemDAO {
	
	@Autowired
	private ShopMapper shopMapper;

    @Override
    public Shop getItemDetailById(Integer id) {
    	Shop shop = shopMapper.selectByPrimaryKey(id);
        return shop;   
    }

    @Override
    public List<Shop> getItemList(Integer type, Integer offset) {
    	List<Shop> shopList = shopMapper.getItemList(type,offset*2);
        return shopList;
    }
   
    @Override
    public int save(Shop shop){
    	int n = shopMapper.insert(shop);
    	if(n>0){
    		return n;
    	}
    	return -1;
    }

}
