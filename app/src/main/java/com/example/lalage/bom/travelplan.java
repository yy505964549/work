package com.example.lalage.bom;


import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

public class travelplan extends BmobObject{
    private String username;
    private String O;
    private String D;
    private String time;

    public String getD() {
        return D;
    }

    public String getO() {
        return O;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public void setD(String d) {
        D = d;
    }

    public void setO(String o) {
        O = o;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
