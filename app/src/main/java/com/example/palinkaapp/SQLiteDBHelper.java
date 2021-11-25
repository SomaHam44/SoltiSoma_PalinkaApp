package com.example.palinkaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLiteDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "palinkak.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "palinka";
    private static final String COL_ID = "id";
    private static final String COL_FOZO = "fozo";
    private static final String COL_GYUMOLCS = "gyumolcs";
    private static final String COL_ALKOHOL = "alkohol";

    public SQLiteDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME +" (" + COL_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FOZO + " TEXT NOT NULL, " + COL_GYUMOLCS +
                " TEXT NOT NULL, " + COL_ALKOHOL + " INTEGER NOT NULL " +
                ");";
        sqLiteDatabase.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean felvesz(String fozo, String gyumolcs, int alkohol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FOZO, fozo);
        values.put(COL_GYUMOLCS, gyumolcs);
        values.put(COL_ALKOHOL, alkohol);
        return db.insert(TABLE_NAME, null, values) != -1;
    }

    public Cursor listaz() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, new String[]{COL_ID,COL_FOZO,COL_GYUMOLCS,COL_ALKOHOL},
                null,null,null,null,null);

    }

    public Cursor kereses(String fozo, String gyumolcs) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT " + COL_ALKOHOL + " FROM " + TABLE_NAME + " WHERE " +
                COL_FOZO + " = ? AND " + COL_GYUMOLCS + " = ?", new String[]{fozo, gyumolcs});
    }
}
