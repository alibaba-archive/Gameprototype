package org.gameprototype.cache.item;

import java.util.List;

import org.gameprototype.dao.model.Shop;

/**
 * Created by chao.zhangch on 15-9-16.
 */
public interface IItemCache {

    /**
     * 获取商品详细信息
     */
	Shop getItemDetailById(Integer id);

    /**
     * 获取某类商品列表
     */
    List<Shop> getItemList(Integer type,Integer offset);
}
