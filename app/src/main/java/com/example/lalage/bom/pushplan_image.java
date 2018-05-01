package com.example.lalage.bom;

import cn.bmob.v3.BmobObject;

public class pushplan_image extends BmobObject {
    private String useiname,url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUseiname() {
        return useiname;
    }

    public void setUseiname(String useiname) {
        this.useiname = useiname;
    }
}
