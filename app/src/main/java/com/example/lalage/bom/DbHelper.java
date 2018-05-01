package com.example.lalage.bom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    //数据库版本号
    private static final int DATABASE_VERSION=4;

    //数据库名称
    private static final String DATABASE_NAME="crud.db";

    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Db_user.TABLE);
        //再次创建表
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+ Db_plan.TABLE);
        //再次创建表
        onCreate(db);
    }
}
