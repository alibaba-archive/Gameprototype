package org.gameprototype.dao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuowu.zm on 2015/9/22.
 */
public class Combat implements Serializable {

    /**
     * 每场战斗对应ID
     */
    private Integer combatID;

    private String combatDesc;

    public Integer getCombatID() {
        return combatID;
    }

    public void setCombatID(Integer combatID) {
        this.combatID = combatID;
    }

    public String getCombatDesc() {
        return combatDesc;
    }

    public void setCombatDesc(String combatDesc) {
        this.combatDesc = combatDesc;
    }
}
