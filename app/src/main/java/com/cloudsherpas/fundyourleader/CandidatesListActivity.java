package com.cloudsherpas.fundyourleader;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class CandidatesListActivity extends ListActivity {

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
            R.mipmap.miriamsantiago,
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
                // TODO Auto-generated method stub
                String candidate = candidates[+position];
                String pledge = pledges[+position];
                Toast.makeText(getApplicationContext(), candidate, Toast.LENGTH_SHORT).show();

            }
        });
    }

}
