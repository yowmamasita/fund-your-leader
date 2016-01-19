package com.cloudsherpas.fundyourleader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cloudsherpas.fundyourleader.model.Candidate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PledgeAmountListActivity extends AppCompatActivity {

    private ListView pledgeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pledge_amount_list);

        Intent intent = getIntent();
        final String candidateName = intent.getStringExtra(MainActivity.CANDIDATE_NAME);

        //Configuration for ListView
        pledgeListView = (ListView) findViewById(R.id.listView);

        //Mock data of pledge amounts
        String[] pledgeAmounts = new String[]{ "100", "500", "1000", "2000", "5000", "10000" };

        //Adapter to display the loaded data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_activated_1, pledgeAmounts
        );

        //Assign adapter to listView
        pledgeListView.setAdapter(adapter);

        //ListView item click listener
        pledgeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pledgeAmountItem = (String) pledgeListView.getAdapter().getItem(position);
//                Toast.makeText(getApplicationContext(), pledgeAmountItem, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), TestimonialActivity.class);
                intent.putExtra(MainActivity.CANDIDATE_NAME, candidateName);
                intent.putExtra(MainActivity.PLEDGE_AMOUNT, pledgeAmountItem);
                startActivity(intent);
            }
        });
    }
}
