package com.lucas.lifechecker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Lucas on 10/14/2017.
 */

public class CounterDbHelper extends SQLiteOpenHelper {

    // Daily Count Table
    public static final String TABLE_DAILY_COUNT =  "tb_daily_count";
    public static final String COLUMN_DATE = "date_pk";
    public static final String COLUMN_DAILY_COUNT = "daily_count";
    public static final String COLUMN_DAY_OF_WEEK = "day_of_week";

    // weekly Count Table
    public static final String TABLE_WEEKLY_COUNT = "tb_weekly_count";
    public static final String COLUMN_WEEK = "week_pk";
    public static final String COLUMN_WEEKLY_COUNT = "weekly_count";
    public static final String COLUMN_DATE_OF_WEEK = "date_of_week";

    public static final int DATABASE_VERSION = 15;
    public static final String DATABASE_NAME = "Counter.db";

    private static final String SQL_CREATE_DAILY_COUNT =
            "CREATE TABLE " + TABLE_DAILY_COUNT + " (" +
                    COLUMN_DATE + " STRING PRIMARY KEY," +
                    COLUMN_DAILY_COUNT + " INTEGER," +
                    COLUMN_DAY_OF_WEEK + " STRING )";

    private static final String SQL_CREATE_WEEKLY_COUNT =
            "CREATE TABLE " + TABLE_WEEKLY_COUNT + " (" +
                    COLUMN_WEEK + " STRING PRIMARY KEY," +
                    COLUMN_WEEKLY_COUNT + " INTEGER," +
                    COLUMN_DATE_OF_WEEK + " INTEGER )";

    public CounterDbHelper( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( SQL_CREATE_DAILY_COUNT );
        db.execSQL( SQL_CREATE_WEEKLY_COUNT );

        ContentValues values = new ContentValues();
        String formattedDate = getCurrentDate();

        values.put( COLUMN_DATE, formattedDate);
        values.put( COLUMN_DAILY_COUNT, 0 );
        db.insert( TABLE_DAILY_COUNT, null, values );
        values.clear();

        String currentWeek = getCurrentWeek();
        String currentDate = getCurrentDate();

        values.put( COLUMN_WEEK, currentWeek );
        values.put( COLUMN_WEEKLY_COUNT, 0 );
        values.put( COLUMN_DATE_OF_WEEK,  currentDate );
        db.insert( TABLE_WEEKLY_COUNT, null, values );
    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_DAILY_COUNT );
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_WEEKLY_COUNT );
        onCreate( db );
    }

    /**
     * Check to see if the current day exists and then add a count to it
     * @return
     */
    public int checkAndAddToCount() {
        SQLiteDatabase db = this.getWritableDatabase();

        String currentDate_string = getCurrentDate();
        Cursor checkIfAdded = db.rawQuery( "SELECT * FROM tb_daily_count WHERE date_pk='"+ currentDate_string +"'", null);
        checkIfAdded.moveToFirst();

        if( checkIfAdded.getCount() == 0 ) {
            addNewDay( currentDate_string );
        }

        addToWeek(db);
        checkIfAdded.close();

        return addToDailyCount( db );
    }

    /**
     * Check to see if this week exists and create the week if it doesn't and add a count to it
     * @param db - the db
     */
    private void addToWeek( SQLiteDatabase db ){
        String currentWeek = getCurrentWeek();
        Cursor checkIfAdded = db.rawQuery( "SELECT * FROM tb_weekly_count WHERE week_pk='"+ currentWeek + "'", null );

        if( checkIfAdded.getCount() == 0 ) {
            addNewWeek( currentWeek );
        }
            addtoWeekCount( currentWeek );
        checkIfAdded.close();
    }

    /**
     * Add another view to our current week
     * @param currentWeek string - the current week
     */
    private void addtoWeekCount( String currentWeek ) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor currentWeekCount = db.rawQuery( "SELECT * FROM tb_weekly_count WHERE week_pk='" + currentWeek + "'", null );
        currentWeekCount.moveToFirst();
        int weekCount = currentWeekCount.getInt(1);
        currentWeekCount.close();

        Cursor weeklyCount = db.rawQuery( "UPDATE tb_weekly_count SET weekly_count="+ ++weekCount + "" +
                " WHERE week_pk='" + currentWeek + "'", null );
        weeklyCount.moveToFirst();
        weeklyCount.close();
    }

    /**
     * Add a new week to our db
     * @param currentWeek string - the current week
     */
    private void addNewWeek( String currentWeek ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        String currentDate = getCurrentDate();

        values.put( COLUMN_WEEK, currentWeek );
        values.put( COLUMN_WEEKLY_COUNT, 0 );
        values.put( COLUMN_DATE_OF_WEEK,  currentDate );
        db.insert( TABLE_WEEKLY_COUNT, null, values );
    }

    /**
     * Add a new week to our db
     * @param currentDate string - the current week
     */
    private void addNewDay( String currentDate ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put( COLUMN_DATE, currentDate );
        values.put( COLUMN_DAILY_COUNT, 0 );
        values.put( COLUMN_DAY_OF_WEEK, getDayofWeek());
        db.insert( TABLE_DAILY_COUNT, null, values );
    }

    /**
     * Add to the day's count
     * @param db sqlitedatabse - the db
     * @return Current day count
     */
    private int addToDailyCount( SQLiteDatabase db) {
        String currentDate = getCurrentDate();
        Cursor currentDateCount = db.rawQuery( "SELECT * FROM tb_daily_count WHERE date_pk='"+ currentDate +"'", null);

        currentDateCount.moveToFirst();
        int currentDay_Count = currentDateCount.getInt(1);
        currentDateCount.close();

        Cursor dailyCount = db.rawQuery( "UPDATE tb_daily_count SET daily_count='"+ ++currentDay_Count +"'" +
                " WHERE date_pk='"+ currentDate +"'", null);
        dailyCount.moveToFirst();
        dailyCount.close();

        return currentDay_Count;
    }

    /**
     * Obtain the current date
     * @return string - representation of the date (YYYY-MM-DD)
     */
    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get( Calendar.DAY_OF_MONTH );
        int month = calendar.get( Calendar.MONTH );
        int year = calendar.get( Calendar.YEAR );
        return year + "-" + ++month + "-" + day;
    }

    /**
     * Obtain the current week of the year
     * @return int - week of the year
     */
    private String getCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get( Calendar.WEEK_OF_YEAR );
        int year = calendar.get( Calendar.YEAR );
        return week + "-" + year;
    }

    private int getDayofWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get( Calendar.DAY_OF_WEEK );
    }

    public int getWeekCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String currentWeek = getCurrentWeek();

        Cursor currentWeekCount = db.rawQuery( "SELECT * FROM tb_weekly_count WHERE week_pk='" + currentWeek + "'", null );
        currentWeekCount.moveToFirst();
        int weekCount = currentWeekCount.getInt(1);
        currentWeekCount.close();

        return weekCount;
    }

    public int getTodayCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String currentDate = getCurrentDate();

        Cursor currentDayCount = db.rawQuery( "SELECT * FROM tb_daily_count WHERE date_pk='" + currentDate + "'", null );
        currentDayCount.moveToFirst();
        int weekCount = currentDayCount.getInt(1);
        currentDayCount.close();

        return weekCount;
    }

    public int[] getDayAverages() {
        SQLiteDatabase db = this.getWritableDatabase();
        String currentDate = getCurrentDate();

        int[] totals = new int[7];
        int[] numberOfDays = new int[7];

        Cursor previousDays_cursor = db.rawQuery( "SELECT * FROM tb_daily_count WHERE not date_pk='" + currentDate + "'", null );

        previousDays_cursor.moveToFirst();

        // find the total amount of views per day of week
        // and the number of days per day of week
        for( int i = 0; i < previousDays_cursor.getCount(); i++ ) {
            int day = previousDays_cursor.getInt(2);
            numberOfDays[day]++;
            totals[day] += previousDays_cursor.getInt(1);
            previousDays_cursor.moveToNext();
        }

        for( int i = 0; i < totals.length; i++ ) {
            totals[i] = totals[i] / numberOfDays[i];
        }

        previousDays_cursor.close();
        return totals;
    }

}
