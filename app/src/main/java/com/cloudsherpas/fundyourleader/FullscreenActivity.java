package com.cloudsherpas.fundyourleader;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import com.cloudsherpas.fundyourleader.db.CandidateContract;
import com.cloudsherpas.fundyourleader.db.CandidateDbHelper;
import com.firebase.client.Firebase;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Firebase.setAndroidContext(this);
        CandidateDbHelper mDbHelper = new CandidateDbHelper(getApplicationContext());

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CandidateContract.CandidateEntry.COLUMN_NAME_NAME, "Binay");
        db.insert(CandidateContract.CandidateEntry.TABLE_NAME, "", values);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
