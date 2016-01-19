package com.cloudsherpas.fundyourleader.db;

/**
 * Created by ben on 1/19/16.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;


public class CandidateDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FYL.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CandidateContract.CandidateEntry.TABLE_NAME + " (" +
                    CandidateContract.CandidateEntry._ID + " INTEGER PRIMARY KEY," +
                    CandidateContract.CandidateEntry.COLUMN_FIRST_NAME + " TEXT," +
                    CandidateContract.CandidateEntry.COLUMN_LAST_NAME + " TEXT," +
                    CandidateContract.CandidateEntry.COLUMN_BIRTHDAY + " TEXT" +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CandidateContract.CandidateEntry.TABLE_NAME;

    public CandidateDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
