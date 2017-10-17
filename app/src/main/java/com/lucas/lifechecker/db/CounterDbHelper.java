package com.lucas.lifechecker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.icu.text.UnicodeSetSpanner;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.Date;

/**
 * Created by Lucas on 10/14/2017.
 */

public class CounterDbHelper extends SQLiteOpenHelper {

    public static final String TABLE_COUNT = "tb_count";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TODAY = "today";
    public static final String COLUMN_WEEK = "week";

    public static final String TABLE_DAILY_COUNT =  "tb_daily_count";
    public static final String COLUMN_DATE = "date_pk";
    public static final String COLUMN_DAILY_COUNT = "daily_count";

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Counter.db";

    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + TABLE_COUNT + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TODAY + " INTEGER," +
                COLUMN_WEEK + " INTERGER )";

    private static final String SQL_CREATE_DAILY_COUNT =
            "CREATE TABLE " + TABLE_DAILY_COUNT + " (" +
                    COLUMN_DATE + " STRING PRIMARY KEY," +
                    COLUMN_DAILY_COUNT + " INTEGER )";

    public CounterDbHelper( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( SQL_CREATE_ENTRIES );
        db.execSQL( SQL_CREATE_DAILY_COUNT);

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, 1);
        values.put(COLUMN_TODAY, 0);
        values.put(COLUMN_WEEK, 0);
        db.insert(TABLE_COUNT, null, values);
        values.clear();

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = date.format(new Date());
        Log.d("DATE", formattedDate );
        values.put( COLUMN_DATE, formattedDate);
        values.put( COLUMN_DAILY_COUNT, 0 );
        db.insert( TABLE_DAILY_COUNT, null, values );
    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_COUNT);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_DAILY_COUNT);
        onCreate( db );
    }

    /**
     * Increases the view count for the current day and current week
     * @return the views for the current day
     */
    public int increaseCounter() {
        // Get our db repo in write mode
        SQLiteDatabase db = this.getReadableDatabase();

        // Grab the current information from our db
        Cursor cursor = db.rawQuery("SELECT * FROM tb_count WHERE id='1'", null);
        cursor.moveToFirst();
        int today = cursor.getInt(1);
        int week = cursor.getInt(2);
        cursor.close();

        // Update our db
        // Will not actually update without the cursor
        Cursor c = db.rawQuery( "UPDATE tb_count SET today='"+ ++today +"'," +
                " week='"+ ++week +"'" +
                " WHERE id='1'", null);
        c.moveToFirst();
        c.close();
        return today;
    }

    public void testDate() {
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String today = date.format(new Date());

        Cursor cursor = db.rawQuery( "SELECT * FROM tb_daily_count WHERE date_pk='"+ today + "'", null);

        cursor.moveToFirst();
        cursor.getString(0);
    }

    public void checkAndAddView() {
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat currentDate = new SimpleDateFormat( "yyyy-MM-dd" );
        String currentDate_string = currentDate.format( new Date() );

        Cursor checkIfAdded = db.rawQuery( "SELECT * FROM tb_daily_count WHERE date_pk='"+ currentDate_string +"'", null);

        if( checkIfAdded.getCount() == 0 ) {
            addToDailyCount( db );
        }
    }

    private int addToDailyCount( SQLiteDatabase db) {

        Cursor cursor = db.rawQuery("SELECT * FROM tb_count WHERE id='1'", null);
        cursor.moveToFirst();
        int today = cursor.getInt(1);
        int week = cursor.getInt(2);

        SimpleDateFormat currentDate = new SimpleDateFormat( "yyyy-MM-dd" );
        String currentDate_string = currentDate.format( new Date() );

        Cursor currentDay = db.rawQuery( "SELECT * FROM tb_daily_count WHERE date_pk='"+ currentDate_string +"'", null);

        currentDay.moveToFirst();
        int currentDay_Count = currentDay.getInt(1);

        Cursor dailyCount = db.rawQuery( "UPDATE tb_count SET daily_count='"+ ++currentDay_Count +"'," +
                " WHERE date_pk='"+ currentDate_string +"'", null);

        dailyCount.moveToFirst();

        return currentDay_Count;
    }
}
