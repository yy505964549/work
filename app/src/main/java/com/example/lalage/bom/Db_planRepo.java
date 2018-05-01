package com.example.lalage.bom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Db_planRepo {
    private DbHelper dbHelper;

    public  Db_planRepo(Context context){
        dbHelper = new DbHelper(context);
    }

    public int insert(Db_plan plan){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Db_plan.KEY_ID,plan.plan_id);
        values.put(Db_plan.KEY_name,plan.plan_username);
        values.put(Db_plan.KEY_O,plan.plan_O);
        values.put(Db_plan.KEY_D,plan.plan_D);
        values.put(Db_plan.KEY_time,plan.plan_time);

        long plan_id = db.insert(Db_plan.TABLE,null,values);
        return  (int)plan_id;
    }
    //获得全部
    public ArrayList<HashMap<String ,String>> getplanlist(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Db_plan.KEY_ID+","+
                Db_plan.KEY_name+
                Db_plan.KEY_O+
                Db_plan.KEY_D+Db_plan.KEY_time+

                " FROM "+Db_plan.TABLE;
        ArrayList<HashMap<String,String>> planlist = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> plan = new HashMap<String, String>();
                plan.put("plan_id",cursor.getString(cursor.getColumnIndex(Db_plan.KEY_ID)));
                plan.put("plan_username",cursor.getString(cursor.getColumnIndex(Db_plan.KEY_name)));
                plan.put("plan_O",cursor.getString(cursor.getColumnIndex(Db_plan.KEY_O)));
                plan.put("plan_D",cursor.getString(cursor.getColumnIndex(Db_plan.KEY_D)));
                plan.put("plan_time",cursor.getString(cursor.getColumnIndex(Db_plan.KEY_time)));
                planlist.add(plan);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return planlist;
    }

    //查询
    public ArrayList<HashMap<String ,String>> getplanbyusername(String username){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery = "SELECT "+
                Db_plan.KEY_ID + "," +
                Db_plan.KEY_name+
                "," +
                Db_plan.KEY_O+
                "," +
                Db_plan.KEY_D+
                "," +
                Db_plan.KEY_time+" WHERE " +
                Db_plan.KEY_name + "=?";
        ArrayList<HashMap<String,String>> planlist = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{username});
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> plan = new HashMap<String, String>();
                plan.put("plan_id",cursor.getString(cursor.getColumnIndex(Db_plan.KEY_ID)));
                plan.put("plan_username",cursor.getString(cursor.getColumnIndex(Db_plan.KEY_name)));
                plan.put("plan_O",cursor.getString(cursor.getColumnIndex(Db_plan.KEY_O)));
                plan.put("plan_D",cursor.getString(cursor.getColumnIndex(Db_plan.KEY_D)));
                plan.put("plan_time",cursor.getString(cursor.getColumnIndex(Db_plan.KEY_time)));
                planlist.add(plan);


            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return planlist;

    }
}
