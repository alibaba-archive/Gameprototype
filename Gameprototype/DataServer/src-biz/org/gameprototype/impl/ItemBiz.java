package org.gameprototype.impl;

import java.util.List;

import org.gameprototype.IItemBiz;
import org.gameprototype.cache.item.IItemCache;
import org.gameprototype.dao.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemBiz")
public class ItemBiz implements IItemBiz {

    @Autowired
    private IItemCache itemCache;
    
    @Override
    public Shop getItemDetailById(Integer id) {
        // TODO Auto-generated method stub
        return itemCache.getItemDetailById(id);
    }

    @Override
    public List<Shop> getItemList(Integer type, Integer offset) {
        // TODO Auto-generated method stub
        return itemCache.getItemList(type, offset);
    }

}
