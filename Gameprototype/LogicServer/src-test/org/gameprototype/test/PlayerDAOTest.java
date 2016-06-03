package org.gameprototype.test;

import org.gameprototype.dao.IPlayerEquipDAO;
import org.gameprototype.dao.IPlayerModelDAO;
import org.gameprototype.dao.model.PlayerEquip;
import org.gameprototype.dao.model.PlayerModel;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by haihong.xiahh on 2015/9/15.
 */
public class PlayerDAOTest extends SpringJunitTest {
    @Resource
    private IPlayerModelDAO playerModelDAO;
    @Resource
    private IPlayerEquipDAO playerEquipDAO;

    @Test
    public void testPlayerModelOperation() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName("test");
        playerModel.setInfo("test");
        playerModel.setDamage(100);
        playerModel.setHitpoints(100);
        playerModel.setImg("sth");
        Assert.assertEquals(null, playerModel.getId());

        // insert
        int retInsert = playerModelDAO.insert(playerModel);
        Assert.assertTrue(playerModel.getId() > 1);
        Assert.assertEquals("insert player model", 1, retInsert);

        // get
        PlayerModel playerModelAfterInsert = playerModelDAO.get(playerModel.getId());
        Assert.assertTrue(playerModel.equals(playerModelAfterInsert));

        // update
        playerModel.setName("test2");
        int retUpdate = playerModelDAO.update(playerModel);
        Assert.assertEquals("update player model", 1, retUpdate);

        // get after update
        PlayerModel playerModelAfterUpdate = playerModelDAO.get(playerModel.getId());
        Assert.assertTrue(playerModel.equals(playerModelAfterUpdate));

        // get list
        List<PlayerModel> modelList = playerModelDAO.getList();
        Assert.assertTrue(modelList.size() > 0);

        // delete
        int retDelete = playerModelDAO.delete(playerModel.getId());
        Assert.assertEquals("delete player model", 1, retUpdate);

        // get after delete
        PlayerModel playerModelAfterDelete = playerModelDAO.get(playerModel.getId());
        Assert.assertNull(playerModelAfterDelete);
    }

    @Test
    public void testPlayerEquipmentOperation() {
        // new PlayerEquip
        Integer playerRoleId = 1;
        Integer equipId = 1;
        PlayerEquip playerEquip = new PlayerEquip();
        playerEquip.setEquipId(equipId);
        playerEquip.setPlayerId(playerRoleId);
        playerEquip.setStatus(0);

        // insert
        Assert.assertEquals(null, playerEquip.getId());
        int retInsert = playerEquipDAO.insert(playerEquip);
        Assert.assertTrue(playerEquip.getId() > 0);
        Assert.assertEquals("insert player quip", 1, retInsert);

        // get item after insert
        PlayerEquip playerEquipAfterInsert = playerEquipDAO.get(playerEquip.getId());
        Assert.assertTrue(playerEquip.equals(playerEquipAfterInsert));

        // get list after insert
        List<PlayerEquip> playerEquipList = playerEquipDAO.getList(playerRoleId);
        Assert.assertTrue(playerEquipList.size() > 0);
        boolean getValid = false;
        for (PlayerEquip tmp : playerEquipList) {
            if(tmp.equals(playerEquip)) {
                getValid = true;
                break;
            }
        }
        Assert.assertTrue(getValid);

        // update
        playerEquip.setStatus(1);
        int retUpdate = playerEquipDAO.updateStatus(1, playerEquip.getId());
        Assert.assertEquals("update player quip", 1, retUpdate);

        // get after update
        playerEquipList = playerEquipDAO.getList(playerRoleId);
        Assert.assertTrue(playerEquipList.size() > 0);
        getValid = false;
        for (PlayerEquip tmp : playerEquipList) {
            if(tmp.equals(playerEquip)) {
                getValid = true;
                break;
            }
        }
        Assert.assertTrue(getValid);

        // delete
        int retDelete = playerEquipDAO.delete(playerEquip);
        Assert.assertEquals("delete player quip", 1, retUpdate);

        // get after delete
        PlayerEquip playerEquipAfterDelete = playerEquipDAO.get(playerEquip.getId());
        Assert.assertNull(playerEquipAfterDelete);

    }
}
