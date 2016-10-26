package com.github.nkzawa.socketio.androidchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_EMAIL = "email";
    public  static final String COLUMN_WEBSITE = "website";
    public static final String COLUMN_QUALIFICATIONS = "qualifications";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_CATEGORY ="category";

    /****************************************************************
     * DATABASE INITIALIZATION W/ NAME AND VERSION NUMBER
     ***************************************************************/
    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION); }
    /****************************************************************
     * CREATE TABLE ON START
     ***************************************************************/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                   COLUMN_NAME + " TEXT, " +
                   COLUMN_NUMBER + " INTEGER, " +
                   COLUMN_EMAIL + " TEXT, " +
                   COLUMN_WEBSITE + " TEXT, " +
                   COLUMN_QUALIFICATIONS + " TEXT, " +
                   COLUMN_DETAILS + " TEXT, " +
                   COLUMN_CATEGORY + " TEXT)" ); }
    /****************************************************************
     * UPGRADE RECORD
     ***************************************************************/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db); }
    /****************************************************************
     * INSERT
     ***************************************************************/
    public boolean insert(String name, int number, String email, String website, String qualifications, String details, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_NUMBER, number);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_WEBSITE, website);
        contentValues.put(COLUMN_QUALIFICATIONS, qualifications);
        contentValues.put(COLUMN_DETAILS, details);
        contentValues.put(COLUMN_CATEGORY, category);
        db.insert(TABLE_NAME, null, contentValues);
        return true; }
    /****************************************************************
     * NUMBER OF ROWS
     ***************************************************************/
    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows; }
    /****************************************************************
     * INSERT
     ***************************************************************/
    public boolean update(Integer id, String name, int number, String email, String website, String qualifications, String details, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_NUMBER, number);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_WEBSITE, website);
        contentValues.put(COLUMN_QUALIFICATIONS, qualifications);
        contentValues.put(COLUMN_DETAILS, details);
        contentValues.put(COLUMN_CATEGORY, category);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true; }
    /****************************************************************
     * DELETE
     ***************************************************************/
    public Integer delete(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[] { Integer.toString(id) }); }
    /****************************************************************
     * GET RECORD
     ***************************************************************/
    public Cursor getRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?", new String[]{Integer.toString(id)});
        return res; }
    /****************************************************************
     * GET ALL RECORD
     ***************************************************************/
    public Cursor getAllRecords() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + TABLE_NAME, null );
        return res; }
}