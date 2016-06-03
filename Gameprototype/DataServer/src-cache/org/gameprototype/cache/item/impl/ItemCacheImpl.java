package org.gameprototype.cache.item.impl;


import java.util.List;

import org.gameprototype.base.redis.KeysTools;
import org.gameprototype.base.redis.RedisTools;
import org.gameprototype.cache.item.IItemCache;
import org.gameprototype.dao.IItemDAO;
import org.gameprototype.dao.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chao.zhangch on 2015/9/16.
 */

@Service("itemCache")
public class ItemCacheImpl   implements IItemCache {
	
	@Autowired
	private RedisTools redisTools;
	
	@Autowired
	private IItemDAO itemDAO;
	
    @Override
    public Shop getItemDetailById(Integer id) {
    	
		String itemKey=KeysTools.ITEMKEYS.itemKey(id);
		Object item=redisTools.OBJECTS.get(itemKey);
		if(item==null){
	    	Shop shop = itemDAO.getItemDetailById(id);
			if(shop==null)
				return null;
			else{
				redisTools.OBJECTS.set(itemKey, shop);
				return shop;
			}
		}
		return (Shop)item;
    }

    @Override
    public List<Shop> getItemList(Integer itemtype, Integer itemOffset) {
    	
    	String itemlistKey=KeysTools.ITEMKEYS.itemlistKey(itemtype,itemOffset);
		Object itemlist=redisTools.OBJECTS.get(itemlistKey);
		if(itemlist==null){
	    	List<Shop> shopList = itemDAO.getItemList(itemtype,itemOffset*2);
			if(shopList==null)
				return null;
			else{
				redisTools.OBJECTS.set(itemlistKey, shopList);
				return shopList;
			}
		}
		return (List<Shop>)itemlist;
    }
   }
