package com.example.lalage.bom;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class userindexActivity extends AppCompatActivity {

    private Button Logout,Upload,Checkplan,checkputplan,uploadvideo,playvideo,downloadpdf,checkdb,insertdb,creatdb,uploadpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.userindex);
        Bmob.initialize(this, "a44b90433ea7c49b1ba61f3cb0a483da");

        //注销
        Logout = (Button) findViewById(R.id.logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();
                User currentUserr = BmobUser.getCurrentUser(User.class);
                Intent intent = new Intent(userindexActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //上传旅游计划
        Upload = (Button)findViewById(R.id.upload);
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userindexActivity.this,uploadplanActivity.class);
                startActivity(intent);
            }
        });

        //查看旅游计划
        Checkplan = (Button)findViewById(R.id.but_check);
        Checkplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userindexActivity.this,checkplan.class);
                startActivity(intent);
            }
        });
        //查看推送计划
        checkputplan = (Button)findViewById(R.id.but_check_put);
        checkputplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userindexActivity.this,checkputplanActivity.class);
                startActivity(intent);
            }
        });
        //上传视频
        uploadvideo =(Button)findViewById(R.id.but_uploadfile);
        uploadvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(userindexActivity.this,uploadvideoActivity.class);
                startActivity(intent3);
            }
        });
        //播放视频
        playvideo=(Button)findViewById(R.id.but_playvideo);
        playvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(userindexActivity.this,play_video_Activity.class);
                startActivity(intent3);
            }
        });
        //下载pdf
        downloadpdf=(Button)findViewById(R.id.but_downloadpdf);
        downloadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(userindexActivity.this,Pdf_Activity.class);
                startActivity(intent3);
            }
        });
        //查看sqliteplan
        checkdb=(Button)findViewById(R.id.but_chek_Db) ;
        checkdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(userindexActivity.this,Db_chek_Activity.class);
                startActivity(intent3);
            }
        });
        //下载plantosqlite
        insertdb=(Button)findViewById(R.id.but_plantodb);
        insertdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(userindexActivity.this,Dload_to_Sqlite_Activity.class);
                startActivity(intent3);
            }
        });
        //上传pdf
        uploadpdf=(Button)findViewById(R.id.but_upload_pdf);
        uploadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(userindexActivity.this,up_load_pdf_Activity.class);
                startActivity(intent3);
            }
        });


    }
}
