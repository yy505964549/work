package com.example.lalage.bom;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String phone;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
