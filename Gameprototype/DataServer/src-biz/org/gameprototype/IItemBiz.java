package org.gameprototype;

import java.util.List;

import org.gameprototype.dao.model.Shop;

public interface IItemBiz {
    /**
     * 获取商品详细信息
     */
    Shop getItemDetailById(Integer id);

    /**
     * 获取某类商品列表
     */
    List<Shop> getItemList(Integer type,Integer offset);
}
