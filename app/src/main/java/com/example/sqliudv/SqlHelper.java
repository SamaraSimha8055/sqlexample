package com.example.sqliudv;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {
    public SqlHelper(Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, contact TEXT, dob TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");

    }
    public Boolean insertuserdata(String name, String contact, String dob)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);

        long result = DB.insert("UserDetails", null, contentValues);
        return result != -1;

    }
    public Boolean updateuserdata(String name, String contact, String dob)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount()>0) {
            long result = DB.update("UserDetails", contentValues, "name=?", new String[]{name});
            return result != -1;
        }
        else {
            return false;
        }

    }
    public Boolean deletedata(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount()>0) {
            long result = DB.delete("UserDetails", "name=?", new String[]{name});
            return result != -1;
        }
        else {
            return false;
        }

    }
    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from Userdetails", null);

    }
}
