package com.example.lalage.bom;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Dload_to_Sqlite_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insetsql);
        Bmob.initialize(this, "a44b90433ea7c49b1ba61f3cb0a483da");
     //   deleteDatabase("test.db");
        final SQLiteDatabase db = openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);


/*
        String CREATE_TABLE_User="CREATE TABLE db_user (_id VARCHAR PRIMARY KEY , name VARCHAR)";
        db.execSQL(CREATE_TABLE_User);
        String CREATE_TABLE_Plan ="CREATE TABLE db_plan (_id VARCHAR PRIMARY KEY , username VARCHAR ,O VARCHAR,D VARCHAR,time VARCHAR)";
        db.execSQL(CREATE_TABLE_Plan);
        */

/*
        SQLiteDatabase db = openOrCreateDatabase("crud.db", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS "+Db_user.TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+Db_plan.TABLE);

        String CREATE_TABLE_User="CREATE TABLE "+ Db_user.TABLE+"("
                +Db_user.KEY_ID+" TEXT PRIMARY KEY AUTOINCREMENT ,"
                +Db_user.KEY_name+" TEXT)";
        db.execSQL(CREATE_TABLE_User);
        String CREATE_TABLE_Plan ="CREATE TABLE "+ Db_plan.TABLE+"("
                +Db_plan.KEY_ID+" TEXT PRIMARY KEY AUTOINCREMENT ,"
                +Db_plan.KEY_name+"TEXT"
                +Db_plan.KEY_O+"TEXT"
                +Db_plan.KEY_D+"TEXT"
                +Db_plan.KEY_time+" TEXT)";
        db.execSQL(CREATE_TABLE_Plan);
        */

     //   Toast.makeText(Dload_to_Sqlite_Activity.this,"chuangjian",Toast.LENGTH_LONG).show();

        User user = BmobUser.getCurrentUser(User.class);
        String userid = user.getUsername();
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("username",userid);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if(e==null){
                    Toast.makeText(Dload_to_Sqlite_Activity.this,"zheli",Toast.LENGTH_LONG).show();
                    for(User user :list){
                        user.getObjectId();
                        user.getUsername();
                        String insert = "INSERT INTO db_user VALUES ( '"+user.getObjectId().toString()+"','"+user.getUsername().toString()+"')";
                        db.execSQL(insert);

                        String select = "SELECT * FROM db_user ";
                        ArrayList<HashMap<String,String>> sss = new ArrayList<HashMap<String, String>>();
                        Cursor cursor = db.rawQuery(select,null);
                        if(cursor.moveToFirst()){
                            do{
                                HashMap<String,String> uu = new HashMap<String, String>();
                                uu.put("user_ID",cursor.getString(cursor.getColumnIndex("_id")));
                                uu.put("user_name",cursor.getString(cursor.getColumnIndex("name")));
                                Toast.makeText(Dload_to_Sqlite_Activity.this,cursor.getString(cursor.getColumnIndex("name")),Toast.LENGTH_LONG).show();
                                sss.add(uu);
                            }while (cursor.moveToNext());
                        }



                    }
                }
            }
        });

        BmobQuery<travelplan> qq = new BmobQuery<travelplan>();
        qq.addWhereEqualTo("username",userid);
        qq.findObjects(new FindListener<travelplan>() {
            @Override
            public void done(List<travelplan> list, BmobException e) {
                if(e==null){
                    for(travelplan plan:list){
                        plan.getObjectId();
                        plan.getUsername();
                        plan.getO();
                        plan.getD();
                        plan.getTime();
                        String insert = "INSERT INTO db_plan  VALUES ( '"+plan.getObjectId().toString()+"','"
                                                                        +plan.getUsername().toString()+"','"
                                                                        +plan.getO().toString()+"','"
                                                                        +plan.getD().toString()+"','"
                                                                        +plan.getTime().toString()+"')";
                        db.execSQL(insert);

                    }
                }
            }
        });

    }
}
