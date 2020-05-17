package com.seoul314.seoul314;

import android.widget.Button;

public class UtilDaoImple {

    public static UtilDaoImple instance;
    private String loginId;
    private Button createRoom;

    private UtilDaoImple(){}


    public static UtilDaoImple getInstance(){

        if(instance == null){
            instance = new UtilDaoImple();
        }

        return instance;
    }

    public void setLoginId(String id){
        loginId = id;
    }

    public String getLoginId(){

        return loginId;
    }

    public void setCreateRoom(Button b){
        createRoom = b;
    }

    public Button getCreateRoom(){
        return createRoom;
    }

}
