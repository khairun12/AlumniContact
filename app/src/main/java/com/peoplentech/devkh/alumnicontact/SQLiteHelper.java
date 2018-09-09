package com.peoplentech.devkh.alumnicontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="aluminalpha";

    public static final String TABLE_NAME="alumniuser";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_Blood="blood";

    public static final String Table_Column_3_Address="address";

    public static final String Table_Column_4_Phone="phone";

    public static final String Table_Column_5_Email="email";

    public static final String Table_Column_6_Dept="dept";

    public static final String Table_Column_7_Batch="batch";

    public static final String Table_Column_8_Job="job";

    public static final String Table_Column_9_Gender="gender";


    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Blood+" VARCHAR, "+Table_Column_3_Address+" VARCHAR, "+Table_Column_4_Phone+" VARCHAR, "+Table_Column_5_Email+" VARCHAR, "+Table_Column_6_Dept+" VARCHAR, "+Table_Column_7_Batch+" VARCHAR,"+Table_Column_8_Job+" VARCHAR,"+Table_Column_9_Gender+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addUser(String uid, String name, String blood, String address, String phone, String email, String dept, String batch, String job, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Table_Column_ID, uid);

        values.put(Table_Column_1_Name, name);

        values.put(Table_Column_2_Blood, blood);

        values.put(Table_Column_3_Address, address);

        values.put(Table_Column_4_Phone, phone);

        values.put(Table_Column_5_Email, email);

        values.put(Table_Column_6_Dept, dept);

        values.put(Table_Column_7_Batch, batch);

        values.put(Table_Column_8_Job, job);

        values.put(Table_Column_9_Gender, gender);

        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        //log.d(TAG, "New user inserted into database: " + id);

    }

    public HashMap<String,String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        db.close();
        return user;
    }

     public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
     }

}