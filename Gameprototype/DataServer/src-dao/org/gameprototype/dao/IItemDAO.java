package org.gameprototype.dao;

import org.gameprototype.dao.model.Shop;

import java.lang.Integer;
import java.util.List;

/**
 * Created by chao.zhangch on 2015/9/11.
 */
public interface IItemDAO {
    /**
     * 获取商品详细信息
     */
	Shop getItemDetailById(Integer id);

    /**
     * 获取某类商品列表
     */
    List<Shop> getItemList(Integer type,Integer offset);
    
    int save(Shop shop);
}
