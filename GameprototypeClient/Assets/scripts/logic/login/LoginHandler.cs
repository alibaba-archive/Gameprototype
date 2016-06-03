using UnityEngine;
using System.Collections;

/// <summary>
/// 登录模块
/// </summary>
public class LoginHandler : MonoBehaviour {


   public void messageReceived(SocketModelPB model) {
       switch (model.Command) { 
           case LoginProtocol.LOGIN_SRES:
               loginResult(model);
               break;
           case LoginProtocol.CREATE_ACCOUNT_SRES:
               regResult(model);
               break;
       }

    }

   private void loginResult(SocketModelPB model)
   {
       CommonResp resp = CommonResp.ParseFrom(model.Content);
       CommonResp.Types.RespCode result = resp.Code;
       switch (result) { 
        case CommonResp.Types.RespCode.LOGIN_ACCOUNT_UNEXIST:
            ClientDataUtil.warnings.Add("帐号不存在，登录失败");
            break;
        case CommonResp.Types.RespCode.LOGIN_PASSWORD_ERROR:
            ClientDataUtil.warnings.Add("密码错误，登录失败");
            break;
        case CommonResp.Types.RespCode.LOGIN_ACCOUNT_ALREADY_ONLINE:
            ClientDataUtil.warnings.Add("帐号已登录，登录失败");
            break;
        default:
            //TODO 登录成功 跳转到角色选择场景
            SendMessage("loading", 1);
            break;
    }
   }
   private void regResult(SocketModelPB model)
   {
       CommonResp resp = CommonResp.ParseFrom(model.Content);
        CommonResp.Types.RespCode result = resp.Code;
       switch (result)
       {
           case CommonResp.Types.RespCode.SUCCESS:
               ClientDataUtil.warnings.Add("注册成功");
               SendMessage("regCannel");               
               break;
           case CommonResp.Types.RespCode.REGISTER_ACCOUNT_ALREADY_EXISTS:
               ClientDataUtil.warnings.Add("帐号已存在，注册失败");
               SendMessage("regClick");
               
               break;
           case CommonResp.Types.RespCode.REGISTER_ACCOUNT_NAME_ILLEGAL:
               ClientDataUtil.warnings.Add("帐号不合法，注册失败");
               SendMessage("regClick");

               break;
           case CommonResp.Types.RespCode.REGISTER_ACCOUNT_PASSWORD_ILLEGAL:
               ClientDataUtil.warnings.Add("密码不合法，注册失败");
               SendMessage("regClick");
               break;
       }
   }
}
