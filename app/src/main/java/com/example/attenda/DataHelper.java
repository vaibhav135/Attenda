package com.example.attenda;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DataHelper extends SQLiteOpenHelper {

    DataBridge dbg = new DataBridge();

    public static final String database_name = "studentInfo.db";
    public static final String tableName = "studentInfo";
    public static final String col0 = "id";
    public static final String col1 = "Name";
    public static final String col2 = "RollNo";
    public static final String col3 = "Gender";
    public static final String col4 = "sem";
    public static final String col5 = "skill";
    public static final String col6 = "DateOfBirth";
    public static final String col7= "course";
    public static final String col8= "Activity";

    public DataHelper(@Nullable Context context) {
        super(context, database_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INFO_TABLE = "CREATE TABLE " + tableName + "(id integer primary key autoincrement, Name text ,RollNo text,Gender text,DateOfBirth text,course text,sem text,skill text,Activity text)";
        db.execSQL(CREATE_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }


    /* public void addData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,dbg.getname());
        contentValues.put(col2,dbg.getRollno());
        contentValues.put(col3,dbg.getGender());
        contentValues.put(col4,dbg.getsem());
        contentValues.put(col5,dbg.getskill());
        contentValues.put(col6,dbg.getdob());
        contentValues.put(col7,dbg.getcourse());
        contentValues.put(col8,dbg.getactivity());
        db.insert(tableName,null,contentValues);
        db.close();
    } */



    public boolean insertData(String name, String rollNo , String Gender , String sem , String skill, String Dob, String course  , String Activity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,name);
        contentValues.put(col2,rollNo);
        contentValues.put(col5,Gender);
        contentValues.put(col3,course);
        contentValues.put(col4,Dob);
        contentValues.put(col6,sem);
        contentValues.put(col7,skill);
        contentValues.put(col8,Activity);
        long result = db.insert(tableName,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}

