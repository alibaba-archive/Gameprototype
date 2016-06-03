using UnityEngine;
using System.Collections;

public class SelectScreen : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    void createClick() {

        SendMessage("loading", 2);
    }
}
