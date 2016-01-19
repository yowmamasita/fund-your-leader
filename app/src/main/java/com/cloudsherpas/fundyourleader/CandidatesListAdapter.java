package com.cloudsherpas.fundyourleader;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CandidatesListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] candidates;
    private final Integer[] imgId;
    private final String[] pledgeAmounts;

    public CandidatesListAdapter(Activity context, String[] candidates, Integer[] imgId, String[] pledgeAmounts) {
        super(context, R.layout.candidates_list_row, candidates);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.candidates = candidates;
        this.imgId = imgId;
        this.pledgeAmounts = pledgeAmounts;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.candidates_list_row, null, true);

        //Candidate details
        TextView txtCandidate = (TextView) rowView.findViewById(R.id.candidateName);
        ImageView imageCandidate = (ImageView) rowView.findViewById(R.id.candidateImage);
        TextView txtPledgeAmount = (TextView) rowView.findViewById(R.id.pledgeAmount);

        txtCandidate.setText(candidates[position]);
        imageCandidate.setImageResource(imgId[position]);
        txtPledgeAmount.setText(pledgeAmounts[position]);

        return rowView;

    };

}
