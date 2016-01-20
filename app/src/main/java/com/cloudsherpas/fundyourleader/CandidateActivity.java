package com.cloudsherpas.fundyourleader;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cloudsherpas.fundyourleader.db.CandidateContract;
import com.cloudsherpas.fundyourleader.db.CandidateDbHelper;
import com.cloudsherpas.fundyourleader.model.Candidate;

public class CandidateActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        Intent intent = getIntent();
        final String candidateName = intent.getStringExtra(CandidatesListActivity.CANDIDATE_NAME);
        final String lastName = candidateName.split(" ")[1];
        Candidate candidate;

        // query candidate full name
        CandidateDbHelper mDbHelper = new CandidateDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String qry = "SELECT firstName, lastName FROM " +
                CandidateContract.CandidateEntry.TABLE_NAME +
                " WHERE " + CandidateContract.CandidateEntry.COLUMN_LAST_NAME + " = ?";
        Cursor cursor = db.rawQuery(qry, new String[] { lastName } );
        cursor.moveToFirst();
        String cFirstName = cursor.getString(cursor.getColumnIndex(CandidateContract.CandidateEntry.COLUMN_FIRST_NAME));
        String cLastName = cursor.getString(cursor.getColumnIndex(CandidateContract.CandidateEntry.COLUMN_LAST_NAME));
        String fullName = cFirstName + " " + cLastName;
        candidate = new Candidate(cFirstName, cLastName, null);
        db.close();
        setTitle(fullName);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), candidate);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PledgeAmountListActivity.class);
                intent.putExtra(CandidatesListActivity.CANDIDATE_NAME, lastName);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_candidate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_candidate, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Candidate candidate;

        public SectionsPagerAdapter(FragmentManager fm, Candidate candidate) {
            super(fm);
            this.candidate = candidate;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f;
            Bundle args = new Bundle();
            args.putString("candidateFirstName", candidate.getFirstName());
            args.putString("candidateLastName", candidate.getLastName());
            switch (position) {
                case 0:
                    f = new InformationFragment();
                    f.setArguments(args);
                    return f;
                case 1:
                    f = new TestimonialFragment();
                    f.setArguments(args);
                    return f;
                case 2:
                    f = new ExpensesFragment();
                    f.setArguments(args);
                    return f;
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Info";
                case 1:
                    return "Testimonials";
                case 2:
                    return "Campaign Expenses";
            }
            return null;
        }
    }

    public static class InformationFragment extends Fragment {

        private String candidateFName;
        private String candidateLName;
        public InformationFragment() { }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.candidateFName = getArguments().getString("candidateFirstName");
            this.candidateLName = getArguments().getString("candidateLastName");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_candidate, null);
//            ImageView imgCandidateImage = (ImageView) view.findViewById(R.id.candidateImage);
//            imgCandidateImage.setImageResource(R.mipmap.binay);
            TextView txtCandidateName = (TextView) view.findViewById(R.id.candidateName);
            txtCandidateName.setText(candidateFName + " " + candidateLName);
            TextView txtPledgeAmount = (TextView) view.findViewById(R.id.pledgeAmount);
            txtPledgeAmount.setText("₱5,000 raised");
            TextView txtCandidateInfo = (TextView) view.findViewById(R.id.candidateInfo);
            txtCandidateInfo.setText(candidateLName + " is a bad person");
            return view;
//            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }
    public static class TestimonialFragment extends Fragment {

        private String candidateName;
        public TestimonialFragment() { }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.candidateName = getArguments().getString("candidateLastName");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.testimonial_list, null);
            ListView pledgeListView = (ListView) view.findViewById(R.id.listView2);
            String pledge = "#whyiamvotingfor" + candidateName + " because he is generous, he gave me ₱500";
            String[] pledgeAmounts = new String[]{ pledge, pledge, pledge, pledge };
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    getContext(), android.R.layout.simple_list_item_activated_1, pledgeAmounts
            );
            pledgeListView.setAdapter(adapter);
            return view;
//            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }
    public static class ExpensesFragment extends Fragment {

        private String candidateName;
        public ExpensesFragment() { }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.candidateName = getArguments().getString("candidateLastName");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            View view = inflater.inflate(R.layout.page1, null);
//            return view;
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

}
