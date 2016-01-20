package com.cloudsherpas.fundyourleader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class CandidatesListActivity extends AppCompatActivity {

    public final static String CANDIDATE_NAME = "com.cloudsherpas.fundyourleader.CANDIDATE_NAME";
    public final static String PLEDGE_AMOUNT = "com.cloudsherpas.fundyourleader.PLEDGE_AMOUNT";

    ListView candidatesListView;

    //Candidates
    String[] candidates = {
            "Rodrigo Duterte",
            "Mar Roxas",
            "Miriam Santiago",
            "Grace Poe",
            "Jejomar Binay",
    };

    //Candidate photos
    Integer[] imgId = {
            R.mipmap.duterte,
            R.mipmap.roxas,
            R.mipmap.santiago,
            R.mipmap.poe,
            R.mipmap.binay
    };

    //Pledge Amounts
    String[] pledges = {
            "₱5,000,000",
            "₱3,500,000",
            "₱8,200,000",
            "₱5,100,000",
            "₱6,800,000",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates_list);

        CandidatesListAdapter adapter = new CandidatesListAdapter(this, candidates, imgId, pledges);
        candidatesListView = (ListView) findViewById(android.R.id.list);
        candidatesListView.setAdapter(adapter);

        candidatesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String candidate = candidates[+position];
                Intent intent = new Intent(getApplicationContext(), CandidateActivity.class);
                intent.putExtra(CANDIDATE_NAME, candidate); // surname
                startActivity(intent);
            }
        });
    }

}
