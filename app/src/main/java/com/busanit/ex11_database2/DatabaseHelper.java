package com.busanit.ex11_database2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "employee.db";
    public static int VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        println("onCreate 호출됨.");
        String sql = "create table if not exists emp("
                +"_id integer PRIMARY KEY autoincrement, "
                +"name text, "
                +"age integer, "
                +"mobile text)";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        println("onOpen 호출됨.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("onUpgrade 호출됨 : "+oldVersion+"->"+newVersion);
        if (newVersion>1){
            db.execSQL("DROP TABLE IF EXISTS emp");
            onCreate(db);
        }
    }

    public void println(String data){
        Log.d("DatabaseHelper",data);
    }
}
