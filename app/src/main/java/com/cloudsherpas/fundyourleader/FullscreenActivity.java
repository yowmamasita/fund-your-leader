package com.cloudsherpas.fundyourleader;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.cloudsherpas.fundyourleader.db.CandidateContract;
import com.cloudsherpas.fundyourleader.db.CandidateDbHelper;
import com.cloudsherpas.fundyourleader.model.Candidate;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
//import com.firebase.client.Firebase;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


public class FullscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
//        Firebase.setAndroidContext(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        String preferencesFileKey = getString(R.string.preference_file_key);
        SharedPreferences sharedPref = this.getSharedPreferences(
                preferencesFileKey, Context.MODE_PRIVATE);
        String firstOpenFlag = getString(R.string.first_open_flag);
        long firstOpen = sharedPref.getInt(firstOpenFlag, 0);
        if (firstOpen == 0) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(firstOpenFlag, 1);
            editor.commit();
            // Database
            CandidateDbHelper mDbHelper = new CandidateDbHelper(getApplicationContext());
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            List<Candidate> candidates = new ArrayList<>();
            candidates.add(new Candidate("Jejomar", "Binay", new LocalDate("1942-11-11")));
            candidates.add(new Candidate("Miriam", "Santiago", new LocalDate("1945-06-15")));
            candidates.add(new Candidate("Rodrigo", "Duterte", new LocalDate("1945-03-28")));
            candidates.add(new Candidate("Grace", "Poe", new LocalDate("1968-09-03")));
            candidates.add(new Candidate("Mar", "Roxas", new LocalDate("1957-05-13")));
            // Insert to db
            for (Candidate candidate : candidates) {
                ContentValues values = new ContentValues();
                values.put(CandidateContract.CandidateEntry.COLUMN_LAST_NAME, candidate.getLastName());
                db.insert(CandidateContract.CandidateEntry.TABLE_NAME, "", values);
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        chooseACandidate(this.findViewById(android.R.id.content));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    public void chooseACandidate(View view) {
        Intent intent = new Intent(this, CandidatesListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void exitApp(View view) {
        finish();
    }
}
