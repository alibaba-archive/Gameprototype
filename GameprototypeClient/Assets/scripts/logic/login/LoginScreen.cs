using UnityEngine;
using System.Collections;
using org.gameprototype.logic.login.dto;

public class LoginScreen : MonoBehaviour {

    public GameObject loginPanel;

    public GameObject regPanel;

    public UILabel accountLabel;
    public UIInput passWordLabel;

    /// <summary>
    /// 注册面板组件
    /// </summary>
    public UILabel regPanelAccount;
    public UIInput regPanelPassWord;
    public UIInput regPanelPassWord1;

    private bool isReg = false;

	void Start () {
	    
	}

    void LoginClick() {
        if (ClientDataUtil.Game_State != GameState.RUNNING) return;
        if (accountLabel.text.Equals(string.Empty)) {
            ClientDataUtil.warnings.Add("帐号格式不正确，请重新输入");
            return;
        }
        if (passWordLabel.value.Equals(string.Empty))
        {
            ClientDataUtil.warnings.Add("密码格式不正确，请重新输入");
            return;
        }
        LoginDTO dto=new LoginDTO();
        dto.account=accountLabel.text;
        dto.passWord=passWordLabel.value;
        LoginDTOPB.Builder builder = LoginDTOPB.CreateBuilder();
        builder.Account = accountLabel.text;
        builder.Password = passWordLabel.value;
        LoginDTOPB loginMsg = builder.Build();
        NetWorkScript.Instance.write(Protocol.TYPE_LOGIN, 0, LoginProtocol.LOGIN_CREQ,  loginMsg.ToByteString());
    }

    void regClick() {
        if (ClientDataUtil.Game_State != GameState.RUNNING) return;
        loginPanel.SetActive(false);
        regPanel.SetActive(true);
        isReg = true;
    }

    void regCannel() {
        if (ClientDataUtil.Game_State != GameState.RUNNING) return;
        regPanel.SetActive(false);
        loginPanel.SetActive(true);
        
    }

    void regOk() {
        if (ClientDataUtil.Game_State != GameState.RUNNING) return;
        if (!isReg) return;
        if (regPanelAccount.text.Equals(string.Empty)) {
            ClientDataUtil.warnings.Add("帐号格式不正确，请重新输入");
            return;
        }
        if (regPanelPassWord.value.Equals(string.Empty))
        {
            ClientDataUtil.warnings.Add("密码格式不正确，请重新输入");
            return;
        }
        if (!regPanelPassWord1.value.Equals(regPanelPassWord.value))
        {
            ClientDataUtil.warnings.Add("两次密码输入不一致，请重新输入");
            return;
        }
        LoginDTOPB.Builder builder = LoginDTOPB.CreateBuilder();
        builder.Account = regPanelAccount.text;
        builder.Password = regPanelPassWord.value;
        LoginDTOPB loginMsg = builder.Build();
        isReg = false;
        NetWorkScript.Instance.write(Protocol.TYPE_LOGIN, 0, LoginProtocol.CREATE_ACCOUNT_CREQ, loginMsg.ToByteString());
        
    }
}
