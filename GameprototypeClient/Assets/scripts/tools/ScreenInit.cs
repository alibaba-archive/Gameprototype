using UnityEngine;
using System.Collections;

public class ScreenInit : MonoBehaviour {

	// Use this for initialization
	void Start () {
        MethodRegister.ResetOrientation(MethodRegister.PORTRAIT);
        NetWorkScript nws= NetWorkScript.Instance;
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
