using UnityEngine;
using System.Collections;

public class Loading : MonoBehaviour {

    public UISprite sprite;
	
	void Update () {
        if (ClientDataUtil.Game_State == GameState.LOADING)
        {
            sprite.fillAmount=ClientDataUtil.loadingProcessValue;
        }
	}
}
