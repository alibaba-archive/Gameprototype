using UnityEngine;
using System.Collections;

public static class MethodRegister{

    /// <summary>
    /// 横屏
    /// </summary>
    public const int LANDSCAPE = 0;
    /// <summary>
    /// 竖屏
    /// </summary>
    public const int PORTRAIT = 1;

    public static void ResetOrientation( int type)
    {
        int value1=0;
        int value2=0;
        switch (type) { 
            case LANDSCAPE:
                value1 = Screen.height;
                value2 = Screen.width;               
                break;
            case PORTRAIT:
                value1 = Screen.width;
                value2 = Screen.height;
                break;
        }     
       

    #if UNITY_ANDROID || UNITY_IPHONE
         if(value1>value2){
            if (Screen.orientation == ScreenOrientation.Landscape || Screen.orientation == ScreenOrientation.LandscapeLeft || Screen.orientation == ScreenOrientation.LandscapeRight)
            {
                Screen.orientation = ScreenOrientation.Portrait;
            }
            else {
                Screen.orientation = ScreenOrientation.Landscape;
            }
        }
    #endif

    }
	
}
