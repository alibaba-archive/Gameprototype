using UnityEngine;
using System.Collections;

public class WarningUtil : MonoBehaviour {

    public GameObject warningWindow;//警告窗口对象
    public UILabel messageLabel;//警告消息标签
	
	void Start () {
	
	}
	
	void Update () {
        if(ClientDataUtil.Game_State==GameState.WARNING)return;
        if (ClientDataUtil.warnings.Count > 0) {
            ClientDataUtil.Game_State = GameState.WARNING;
            warningWindow.SetActive(true);
            messageLabel.text = ClientDataUtil.warnings[0];
            ClientDataUtil.warnings.RemoveAt(0);
        }
	}

    void warningCannel() {
        warningWindow.SetActive(false);
        ClientDataUtil.Game_State = GameState.RUNNING;
    }
}
