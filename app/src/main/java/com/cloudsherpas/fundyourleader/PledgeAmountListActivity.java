package com.cloudsherpas.fundyourleader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PledgeAmountListActivity extends AppCompatActivity {

    private ListView pledgeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pledge_amount_list);

        Intent intent = getIntent();
        final String candidateName = intent.getStringExtra(CandidatesListActivity.CANDIDATE_NAME);

        pledgeListView = (ListView) findViewById(R.id.listView);
        String[] pledgeAmounts = new String[]{ "100", "500", "1000", "2000", "5000", "10000" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_activated_1, pledgeAmounts
        );
        pledgeListView.setAdapter(adapter);

        pledgeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pledgeAmountItem = (String) pledgeListView.getAdapter().getItem(position);
                Intent intent = new Intent(getApplicationContext(), TestimonialActivity.class);
                intent.putExtra(CandidatesListActivity.CANDIDATE_NAME, candidateName);
                intent.putExtra(CandidatesListActivity.PLEDGE_AMOUNT, pledgeAmountItem);
                startActivity(intent);
            }
        });
    }
}
