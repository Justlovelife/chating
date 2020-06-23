package com.example.chating.Bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    //user不需要定义
    //别名
    private  String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;

    }
}
