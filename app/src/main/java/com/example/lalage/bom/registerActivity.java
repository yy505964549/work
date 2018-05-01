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
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class registerActivity extends AppCompatActivity {
    private EditText rusersid,rpassword,phone,ident;
    private Button rregister,rident;
    private int smsid;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Bmob.initialize(this,"a44b90433ea7c49b1ba61f3cb0a483da");
        rusersid = (EditText)findViewById(R.id.regiuserid);
        rpassword = (EditText)findViewById(R.id.regipassword);
        phone = (EditText)findViewById(R.id.phonenum);
        ident = (EditText)findViewById(R.id.iden);//验证码
        rregister = (Button)findViewById(R.id.register);
        rident = (Button) findViewById(R.id.rident);
        //获取验证码
        rident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pnum = phone.getText().toString();
                BmobSMS.requestSMSCode(pnum, "nihao", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if(e==null){
                            Log.i("smile","短信id"+smsid);
                        Toast.makeText(registerActivity.this,"已向您发送验证码",Toast.LENGTH_LONG).show();
                    }
                    }
                });
            }
        });

        //注册
        
        rregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pnum = phone.getText().toString();
                String iden = ident.getText().toString();
                BmobUser.signOrLoginByMobilePhone(pnum, iden, new LogInListener<User>() {
                    @Override
                    public void done(User user,BmobException e){
                        if(user!=null){
                            User u = new User();
                            u.setPhone(phone.getText().toString());
                            u.setUsername(rusersid.getText().toString());
                            u.setPassword(rpassword.getText().toString());
                            u.signUp(new SaveListener<User>() {
                                @Override
                                public void done(User s,BmobException e){
                                    if(e==null){
                                        Toast.makeText(registerActivity.this,"注册成功"+s.toString(),Toast.LENGTH_LONG).show();
                                    }
                                }

                            });
                        }else{
                            Toast.makeText(registerActivity.this,"验证码不对",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}