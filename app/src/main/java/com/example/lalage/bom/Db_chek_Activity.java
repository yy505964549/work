package com.example.lalage.bom;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import cn.bmob.v3.Bmob;

public class Db_chek_Activity extends AppCompatActivity {
    private String mUserName;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_plan);

        final SQLiteDatabase db = openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
        //查找用户
        String select = "SELECT * FROM db_user  ";
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do{
                mUserName = cursor.getString(cursor.getColumnIndex("name"));
            }while (cursor.moveToNext());
        }
        //查找plan根据用户

         select = "SELECT * FROM db_plan WHERE username = '"+mUserName+"' ";
        ArrayList<HashMap<String,String>> sss = new ArrayList<HashMap<String, String>>();
         cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> uu = new HashMap<String, String>();
                uu.put("user_name",cursor.getString(cursor.getColumnIndex("username")));
                uu.put("O",cursor.getString(cursor.getColumnIndex("O")));
                uu.put("D",cursor.getString(cursor.getColumnIndex("D")));
                sss.add(uu);
            }while (cursor.moveToNext());
        }

        //显示
        LinearLayout l = (LinearLayout)findViewById(R.id.lm);
        for(HashMap<String,String> plan:sss){
            TextView tv = new TextView(Db_chek_Activity.this);
            tv.setText(plan.get("user_name")+plan.get("O")+plan.get("D"));
            tv.setTextSize(18);
            l.addView(tv);
        }



    }

}
