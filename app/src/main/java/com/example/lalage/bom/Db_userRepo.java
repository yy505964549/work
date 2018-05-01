package com.example.lalage.bom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Db_userRepo {
    private DbHelper dbHelper;

    public  Db_userRepo(Context context){
        dbHelper = new DbHelper(context);
    }

    public int insert(Db_user user){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Db_user.KEY_ID,user.user_ID);
        values.put(Db_user.KEY_name,user.user_name);
        long user_id = db.insert(Db_user.TABLE,null,values);
        return  (int)user_id;
    }
    //获得全部
    public ArrayList<HashMap<String ,String>> getuserlist(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Db_user.KEY_ID+","+
                Db_user.KEY_name+" FROM "+Db_user.TABLE;
        ArrayList<HashMap<String,String>> userlist = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> user = new HashMap<String, String>();
                user.put("user_ID",cursor.getString(cursor.getColumnIndex(Db_user.KEY_ID)));
                user.put("user_name",cursor.getString(cursor.getColumnIndex(Db_user.KEY_name)));
                userlist.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userlist;
    }

    //查询
    public Db_user getuserbyid(String id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery = "SELECT "+
                Db_user.KEY_ID + "," +
                Db_user.KEY_name+ " WHERE " +
                Db_user.KEY_ID + "=?";
        int iCount=0;
        Db_user user = new Db_user();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{id});
        if(cursor.moveToFirst()){
            do{
                user.user_ID = cursor.getString(cursor.getColumnIndex(Db_user.KEY_ID));
                user.user_name = cursor.getString(cursor.getColumnIndex(Db_user.KEY_name));

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return user;

    }

}
