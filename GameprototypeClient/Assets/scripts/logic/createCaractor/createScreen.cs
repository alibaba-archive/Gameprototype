using UnityEngine;
using System.Collections;

public class createScreen : MonoBehaviour {

//    public GameObject[] points;
    public UIButton choose1;
    public UIButton choose2;
    public UIButton lastSelected ;
    public UIInput nameInput;

//    private Transform target; //相机动画目标点
//    private Transform start;  //相机目标开始点
//    private Camera mainCamera;
    private int selectJob = -1;


	// Use this for initialization
	void Start () {
//        mainCamera = Camera.main;
	}
	
	// Update is called once per frame
	void Update () {

//        if (target) {
//            if (mainCamera.transform.position != AvatarTarget.position) {
//                start = mainCamera.transform.position;
//                mainCamera.transform.position = Vector3.Lerp(strat.position,target.position,Time.deltaTime * 2);
//                mainCamera.transform.position = Quaternion.Lerp(strat.rosition,target.rosition,Time.deltaTime * 2);
//            }else{
//    
//              }
//        }
	    
	}

    void selectJob1() {
//        target = points[0].transform;
        if (lastSelected)
        {
            lastSelected.isEnabled = true;
            
        }
        choose1.isEnabled = false;
        lastSelected = choose1;
        selectJob = 1;
    }

    void selectJob2() {
//        target = points[1].transform;
        if (lastSelected) {
            lastSelected.isEnabled = true;
            
        }
        choose2.isEnabled = false;
        lastSelected = choose2;
        selectJob = 2;
    }

    //创建按钮监听
    void createClick() {
        if (selectJob == -1) {
            ClientDataUtil.warnings.Add("请选择职业");
            return;
        }
        if (nameInput.value.Equals(string.Empty)) {
            ClientDataUtil.warnings.Add("请输入昵称");
            return;
        }
        if (nameInput.value.Length > 12 || nameInput.value.Length < 6)
        {
            ClientDataUtil.warnings.Add("昵称长度不合法");
            return;
        }
        print(nameInput.value.ToString());
        //向服务端发送创建消息

    }
}
