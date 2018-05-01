package com.example.lalage.bom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class DbManager {
    public static final String DB_NAME = "lalage"; //数据库名字
    public static final String PACKAGE_NAME ="com.example.lalage.bom";//包名
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME;
    private SQLiteDatabase db;
    private Context context;

    public DbManager(Context context) {
        this.context = context;
    }


    //获取打开后的数据库
    public SQLiteDatabase getDb() {
        return this.db;
    }

}
