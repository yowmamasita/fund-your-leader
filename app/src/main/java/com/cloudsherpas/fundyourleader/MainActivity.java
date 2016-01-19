package com.cloudsherpas.fundyourleader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list;
    String[] web = {
            "Jejomar \"Jojo\" Cabauatan Binay, Sr",
            "Rodrigo Duterte",
            "Miriam Defensor Santiago",
            "Mary Grace Natividad Poe Llamanzares",
            "Manuel \"Mar\" Araneta Roxas I"
    } ;
    Integer[] imageId = {
            R.drawable.binay,
            R.drawable.duterte,
            R.drawable.miriamsantiago,
            R.drawable.poe,
            R.drawable.roxas

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CandidateList adapter = new
                CandidateList(MainActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();

            }
        });

    }
}
