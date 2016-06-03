package org.gameprototype.test;

import java.util.List;

import javax.annotation.Resource;

import org.gameprototype.dao.IItemDAO;
import org.gameprototype.dao.model.Shop;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by haihong.xiahh on 2015/9/15.
 */
public class ItemDaoTest extends SpringJunitTest {
//    @Resource
//    private IPlayerDAO playerDAO;
    @Resource
    private IItemDAO itemDAO;

    @Test
    public void testsave() {
    	
    	Shop shop = new Shop();
    	shop.setItemName("倚天剑3");
    	shop.setItemType(1);
    	shop.setItemValue(10);
    	shop.setDescription("倚天剑，攻击力超猛");
    	int a = itemDAO.save(shop);
    	Assert.assertEquals(a,1);
    	System.out.println(a);
    }
    
    @Test
    public void testgetItemDetailById() {
    	int id =2;
    	Shop shop = itemDAO.getItemDetailById(id);
    	System.out.println(shop.getItemName());
    	Assert.assertTrue(shop.getItemId()>0);

    }
    
    @Test
    public void testgetItemList() {
    	int itemtype =1;
    	int offset = 1;
    	List<Shop> shoplist = itemDAO.getItemList(itemtype, offset);
    	System.out.println(shoplist.size());
    	Assert.assertEquals(shoplist.size(),2);

    }
}
