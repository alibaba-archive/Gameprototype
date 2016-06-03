using UnityEngine;
using System.Collections;

/// <summary>
/// 消息管理器
/// </summary>
public class NetMessageUtil : MonoBehaviour {

    private LoginHandler login;

	void Start () {
        //初始化获取登录模块
        login = GetComponent<LoginHandler>();
	}
	
	
	void Update () {
        //判断是否有网络消息，有则进行处理
        while (NetWorkScript.Instance.messageList.Count>0)
        {
            messageReceived(NetWorkScript.Instance.messageList[0]);
            NetWorkScript.Instance.messageList.RemoveAt(0);
        }
	}

    void messageReceived(SocketModelPB model) {
        //判断一级协议 确定当前消息属于哪个模块
        switch (model.Type) { 
            case Protocol.TYPE_LOGIN://登录模块
                login.messageReceived(model);
                break;
        }
    }
}
