package org.gameprototype.manager.interfaces;

import org.gameprototype.logic.IHandlerServ;

/**
 * Created by zhoubo on 15-9-6.
 */
public interface IhandlerManager {

    public int register(int area,IHandlerServ handler);
    public void unregister(int area);
    public boolean hasServ(int area);
    public IHandlerServ get(int area);
}
