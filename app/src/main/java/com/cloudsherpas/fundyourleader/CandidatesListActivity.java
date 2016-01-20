package com.cloudsherpas.fundyourleader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class CandidatesListActivity extends AppCompatActivity {

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
            "5000000",
            "3500000",
            "8200000",
            "5100000",
            "6800000",
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
//                Toast.makeText(getApplicationContext(), candidate, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CandidateActivity.class);
                intent.putExtra(MainActivity.CANDIDATE_NAME, candidate);
                startActivity(intent);
            }
        });
    }

}
