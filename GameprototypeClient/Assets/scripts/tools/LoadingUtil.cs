using UnityEngine;
using System.Collections;

public class LoadingUtil : MonoBehaviour {

    public GameObject loadingWindow;
    private AsyncOperation async;
	
	void Update () {
        if(ClientDataUtil.Game_State == GameState.LOADING){
           ClientDataUtil.loadingProcessValue= async.progress;
        }
	}

    void loading(int level) {
        ClientDataUtil.Game_State = GameState.LOADING;
        loadingWindow.SetActive(true);
        StartCoroutine("load", level);
    }

    IEnumerator load(int level) {
        async=Application.LoadLevelAsync(level);
        yield return async;
    }

    void OnLevelWasLoaded(int level) {
        ClientDataUtil.Game_State = GameState.RUNNING;
    }
}
