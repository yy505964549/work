package com.example.lalage.bom;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class video extends BmobObject{
    private String plan_id;
    private BmobFile file;
    public video(String plan_id,BmobFile file){
        this.plan_id = plan_id;

        this.file = file;
    }

    public BmobFile getFile() {
        return file;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setFile(BmobFile file) {
        this.file = file;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }
}
