package com.cloudsherpas.fundyourleader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.cloudsherpas.fundyourleader.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createTestimonial(View view) {
        Intent intent = new Intent(this, TestimonialActivity.class);
        String message = "Binay";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
