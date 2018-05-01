package com.example.lalage.bom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class forgetActivity extends AppCompatActivity {
    private EditText fpassword,fphone ,fident;
    private Button iden,fix;
    private int smsid;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget1);
        Bmob.initialize(this,"a44b90433ea7c49b1ba61f3cb0a483da");
        fpassword = (EditText) findViewById(R.id.newpassword);
        fphone = (EditText) findViewById(R.id.fphonenum);
        fident = (EditText)findViewById(R.id.fiden);
        iden = (Button)findViewById(R.id.getiden);
        fix = (Button)findViewById(R.id.sure);
        //获取验证码
        iden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenum = fphone.getText().toString();
                BmobSMS.requestSMSCode(phonenum, "ok", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if(e==null){
                            Log.i("smile","短信id"+smsid);
                            Toast.makeText(forgetActivity.this,"已向您发送验证码",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        //修改密码
        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = fpassword.getText().toString();
                String ident = fident.getText().toString();
                BmobUser.resetPasswordBySMSCode(ident, pass, new UpdateListener() {
                    @Override
                    public void done(BmobException ex) {
                        if(ex==null){
                            Log.i("smile", "密码重置成功");
                        }else{
                            Log.i("smile", "重置失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
                        }
                    }
                });

            }
        });
    }
}
