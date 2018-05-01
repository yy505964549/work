package com.example.lalage.bom;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Pdf extends BmobObject {
    private String plan_id;
    private BmobFile file;

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public void setFile(BmobFile file) {
        this.file = file;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public BmobFile getFile() {
        return file;
    }
}
