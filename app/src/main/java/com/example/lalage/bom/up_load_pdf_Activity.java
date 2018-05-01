package com.example.lalage.bom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class up_load_pdf_Activity extends AppCompatActivity {
    String musername,plan_id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_pdf);
        Bmob.initialize(this, "a44b90433ea7c49b1ba61f3cb0a483da");
        User u = BmobUser.getCurrentUser(User.class);
        musername = u.getUsername();

        final BmobFile bmobFile = new BmobFile(new File("/sdcard/test.pdf"));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(up_load_pdf_Activity.this,"上传成功",Toast.LENGTH_LONG).show();
                    Pdf p = new Pdf();
                    p.setFile(bmobFile);
                    plan_id="ps8q666U";
                    p.setPlan_id(plan_id);
                    p.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Toast.makeText(up_load_pdf_Activity.this,"绑定上传成功",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(up_load_pdf_Activity.this,"上传失败",Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onProgress(Integer value){
                Toast.makeText(up_load_pdf_Activity.this,"上传"+value+"%",Toast.LENGTH_LONG).show();
            }
        });
    }

}
