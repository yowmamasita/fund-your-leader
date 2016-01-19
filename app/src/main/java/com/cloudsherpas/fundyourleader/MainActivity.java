package com.cloudsherpas.fundyourleader;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.cloudsherpas.fundyourleader.db.CandidateContract;
import com.cloudsherpas.fundyourleader.db.CandidateDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.cloudsherpas.fundyourleader.MESSAGE";
    private List<String> candidates = new ArrayList<>();

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
                candidates.add(lastName);
                cursor.moveToNext();
            }
        }
    }

    public void createTestimonial(View view) {
        Intent intent = new Intent(this, TestimonialActivity.class);
        String candidateName = candidates.get(0);
        intent.putExtra(EXTRA_MESSAGE, candidateName);
        startActivity(intent);
    }
}
