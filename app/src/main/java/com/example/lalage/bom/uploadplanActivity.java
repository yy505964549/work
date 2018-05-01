package com.example.lalage.bom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class uploadplanActivity extends AppCompatActivity {
    private Button btn;
    private EditText O,D,Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadplan);
        Bmob.initialize(this, "a44b90433ea7c49b1ba61f3cb0a483da");

        btn = (Button)findViewById(R.id.but_upload);
        O = (EditText)findViewById(R.id.O);
        D = (EditText)findViewById(R.id.D);
        Date = (EditText)findViewById(R.id.Date);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = BmobUser.getCurrentUser(User.class);
                String username = user.getUsername().toString();
                String O_ =O.getText().toString();
                String D_ =D.getText().toString();
                String Date_=Date.getText().toString();
                travelplan plan = new travelplan();
                plan.setO(O_);
                plan.setD(D_);
                plan.setUsername(username);
                plan.setTime(Date_);
                plan.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(uploadplanActivity.this,"上传成功",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(uploadplanActivity.this,userindexActivity.class);
                            startActivity(intent);
                        }else{

                        }
                    }
                });
            }
        });
    }
}