package com.lucas.lifechecker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Lucas on 10/14/2017.
 */

public final class CounterContract {
    // to prevent someone from accidentally instantiating the contract class,
    // make the contructor private
    private CounterContract() {};

    // Inner class that defines the table comments
    public static class CounterEntry implements BaseColumns {
        public static final String TABLE_COUNT = "tb_count";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TODAY = "today";
        public static final String COLUMN_WEEK = "week";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CounterEntry.TABLE_COUNT + " (" +
                    CounterEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    CounterEntry.COLUMN_TODAY + " INTEGER," +
                    CounterEntry.COLUMN_WEEK + " INTERGER )";

//    public class CounterDbHelper extends SQLiteOpenHelper {
//
//    }
}
