using UnityEngine;
using System.Collections;

public class MyStrech : MonoBehaviour {


    public Agin agin = Agin.Center;

    public float scale_base;

    public float base_width;

    public float base_height;

    public int last_width;
    public int last_height;

    public enum Agin { 
        Width,
        Height,
        Center
    }

	// Use this for initialization
	void Start () {
        reset();
	}
	
	// Update is called once per frame
	void Update () {
        if (Screen.width != last_width || Screen.height != last_height) {
            reset();
            last_width = Screen.width;
            last_height = Screen.height;
        }
	}

    void reset() {
        float value = Screen.height / base_height;

        switch (agin) { 
            case Agin.Center:
                if (Screen.width / base_width < Screen.height / base_height)
                {
                    gameObject.transform.localScale = new Vector3(scale_base * (Screen.width / base_width) / value, scale_base * Screen.width / base_width / value, scale_base * Screen.width / base_width / value);
                }
                else {
                    gameObject.transform.localScale = new Vector3(scale_base, scale_base, scale_base);
                }
                break;
            case Agin.Height:
                gameObject.transform.localScale = new Vector3(scale_base, scale_base, scale_base);
                break;
            case Agin.Width:
                gameObject.transform.localScale = new Vector3(scale_base * (Screen.width / base_width) / value, scale_base * Screen.width / base_width / value, scale_base * Screen.width / base_width / value);
                break;
        }
    }
}
