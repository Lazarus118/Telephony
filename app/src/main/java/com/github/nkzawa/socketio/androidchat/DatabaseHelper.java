package com.github.nkzawa.socketio.androidchat;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelpler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mycontacts.db";
    public static final String TABLE_NAME = "contacts_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "category";
    public static final String COL_3 = "name";
    public static final String COL_4 = "mobileNo";
    public static final String COL_5 = "email";
    public static final String COL_6 = "website";
    public static final String COL_7 = "qualification";
    public static final String COL_8 = "details";

    public DatabaseHelpler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT,category VARCHAR(50),name VARCHAR(80),mobileNo VARCHAR(15),email VARCHAR(100),website VARCHAR(100),qualifications VARCHAR(200),photo BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String category,String name,String mobileNo,String email,String website,String qualifications,String details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,category);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,mobileNo);
        contentValues.put(COL_5,email);
        contentValues.put(COL_6,website);
        contentValues.put(COL_7,qualifications);
        contentValues.put(COL_7,details);
        long result = db.insert(TABLE_NAME, null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean updateData(String id,String category,String name,String mobileNo,String email,String website,String qualifications,String details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,category);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,mobileNo);
        contentValues.put(COL_5,email);
        contentValues.put(COL_6,website);
        contentValues.put(COL_7,qualifications);
        contentValues.put(COL_7,details);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public Cursor getCategories() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor categories = db.rawQuery("select DISTINCT category from " + TABLE_NAME,null);
        return categories;
    }

    public Cursor getFilteredList(String category){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor contactDetails = db.rawQuery("SELECT id,name FROM "+TABLE_NAME+" WHERE category =?",new String[]{category});
        return contactDetails;
    }


    public Cursor getPersonDetails(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor contactDetails = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE id =?",new String[]{String.valueOf(id)});
        return contactDetails;

        // ("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});
        // Cursor cur3 = database2.rawQuery("select max(UnixTimeStamp) from Quote where EmoticonID=? and SubCategoryID=?" ,new String [] {String.valueOf(g),String.valueOf(s)});

    }

}