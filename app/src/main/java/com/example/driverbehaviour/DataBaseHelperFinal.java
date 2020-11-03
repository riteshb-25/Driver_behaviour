package com.example.driverbehaviour;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelperFinal extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gyro_data.db";
    public static final String TABLE_NAME = "final_table";
    public static final String COL = "ID";
    public static final String COL_1 = "time";
    public static final String COL_2 = "gyrox";
    public static final String COL_3 = "gyroy";
    public static final String COL_4 = "gyroz";
    public static final String COL_5 = "accx";
    public static final String COL_6 = "accy";
    public static final String COL_7 = "accz";
    public static final String COL_8 = "latitude";
    public static final String COL_9 = "longitude";
    public static final String COL_10 = "altitude";
    public static final String COL_11 = "speed";

    public DataBaseHelperFinal(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,time LONG ,gyrox FLOAT,gyroy FLOAT,gyroz FLOAT,accx FLOAT,accy FLOAT,accz FLOAT,latitude DOUBLE,longitude DOUBLE,altitude DOUBLE,speed FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(long time,float gyrox,float gyroy,float gyroz,float accx,float accy,float accz,double latitude,double longitude,double altitude,float speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,time);
        contentValues.put(COL_2,gyrox);
        contentValues.put(COL_3,gyroy);
        contentValues.put(COL_4,gyroz);
        contentValues.put(COL_5,accx);
        contentValues.put(COL_6,accy);
        contentValues.put(COL_7,accz);
        contentValues.put(COL_8,latitude);
        contentValues.put(COL_9,longitude);
        contentValues.put(COL_10,altitude);
        contentValues.put(COL_11,speed);
        long result = db.insert(TABLE_NAME, null  ,contentValues);
        if(result == -1){
            //   System.out.println("FALSE ");
            return false;
        }

        else{
            //  System.out.println("TRUE ");
            return true;
        }

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }



}

