package com.cloudsherpas.fundyourleader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PledgeAmountListActivity extends AppCompatActivity {

    private ListView pledgeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pledge_amount_list);

        //Configuration for ListView
        pledgeListView = (ListView) findViewById(R.id.listView);

        //Mock data of pledge amounts
        String[] pledgeAmounts = new String[]{
                "P50.00",
                "P100.00",
                "P200.00",
                "P300.00",
                "P500.00",
                "P1000.00",
                "P1500.00",
                "P2000.00",
                "P3000.00"
        };

        //Candidate pledge amounts


        //Adapter to display the loaded data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.activity_list_item, android.R.id.text1, pledgeAmounts
        );

        //Assign adapter to listView
        pledgeListView.setAdapter(adapter);

        //ListView item click listener
        pledgeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pledgeAmountItem = (String) pledgeListView.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext(), pledgeAmountItem, Toast.LENGTH_SHORT).show();
            }

        });
    }
}
