package com.cloudsherpas.fundyourleader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestimonialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimonial);
        Intent intent = getIntent();
        String candidateName = intent.getStringExtra(CandidatesListActivity.CANDIDATE_NAME);
        EditText editText = (EditText) findViewById(R.id.testimonialText);
        String message = "#" + getString(R.string.hashtag) + candidateName;
        message += " (pledged ₱" + intent.getStringExtra(CandidatesListActivity.PLEDGE_AMOUNT) + ")";
        editText.setText(message);
    }

    public void postTestimonial(View view) {
        Intent intent = new Intent(this, CandidatesListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_SHORT).show();
    }
}
