package org.gameprototype.model;

import java.io.Serializable;

/**
 * Created by zhoubo on 15-9-1.
 */
public class TalkDTO implements Serializable{

    private static final long serialVersionUID = -1886980932315836377L;
    //玩家ID
    private Number userId;
    //玩家名称
    private String userName;
    //消息
    private String message;

    public Number getUserId() {
        return userId;
    }

    public void setUserId(Number userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
