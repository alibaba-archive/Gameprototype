package org.gameprototype.manager;

import org.gameprototype.logic.IHandlerServ;
import org.gameprototype.manager.interfaces.IhandlerManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhoubo on 15-9-6.
 */

public class HandlerManager implements IhandlerManager {

    private Map<Integer,IHandlerServ> handlers=new ConcurrentHashMap<Integer, IHandlerServ>();
    @Override
    public int register(int area, IHandlerServ handler) {
        handler.setArea(area);
        handlers.put(area, handler);
        return area;
    }

    @Override
    public void unregister(int area) {

        handlers.remove(area);

    }

    @Override
    public boolean hasServ(int area) {
        return handlers.containsKey(area);
    }

    @Override
    public IHandlerServ get(int area) {
        return handlers.get(area);
    }
}
