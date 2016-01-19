package com.cloudsherpas.fundyourleader;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;

import com.cloudsherpas.fundyourleader.db.CandidateContract;
import com.cloudsherpas.fundyourleader.db.CandidateDbHelper;
import com.cloudsherpas.fundyourleader.model.Candidate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String CANDIDATE_NAME = "com.cloudsherpas.fundyourleader.CANDIDATE_NAME";
    public final static String PLEDGE_AMOUNT = "com.cloudsherpas.fundyourleader.PLEDGE_AMOUNT";
    private List<Candidate> candidates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CandidateDbHelper mDbHelper = new CandidateDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                CandidateContract.CandidateEntry.COLUMN_LAST_NAME
        };

        Cursor cursor = db.query(CandidateContract.CandidateEntry.TABLE_NAME, projection, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String lastName = cursor.getString(
                        cursor.getColumnIndex(
                                CandidateContract.CandidateEntry.COLUMN_LAST_NAME));
                Log.v("MainActivity/lastName", lastName);
                Candidate candidate = new Candidate(null, lastName, null);
                candidates.add(candidate);
                cursor.moveToNext();
            }
        }
    }

    public void createTestimonial(View view) {
        Intent intent = new Intent(this, PledgeAmountListActivity.class);
        Candidate candidate = candidates.get(2);
        intent.putExtra(CANDIDATE_NAME, candidate.getLastName());
        startActivity(intent);
    }
}
