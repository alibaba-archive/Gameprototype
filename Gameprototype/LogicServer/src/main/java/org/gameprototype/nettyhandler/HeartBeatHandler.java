package org.gameprototype.nettyhandler;

import io.netty.channel.Channel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhoubo on 15-9-1.
 */

@Component
@Service("heartBeatHandler")
public class HeartBeatHandler {

    private Map<Channel,Long> map=new ConcurrentHashMap<Channel, Long>();
    private List<Channel> removeList=new LinkedList<Channel>();

    @Scheduled(cron="0 0/1 *  * * ? ")
    public void excutor(){
        Calendar cd =Calendar.getInstance();
        long now=cd.getTimeInMillis();
        for (Channel value : map.keySet()) {
            long last=map.get(value);
            if((now-last)/1000>35){
                removeList.add(value);
            }
        }
        for (Channel value : removeList) {
            map.remove(value);
            value.close();
        }
        removeList.clear();
    }

    public void received(Channel channel){
        Calendar cd =Calendar.getInstance();
        map.put(channel, cd.getTimeInMillis());
    }

    public void close(Channel channel){
        map.remove(channel);
    }
}
