package com.cloudsherpas.fundyourleader.db;

import android.provider.BaseColumns;

/**
 * Created by ben on 1/19/16.
 */
public class CandidateContract {
    public CandidateContract() {}

    public static abstract class CandidateEntry implements BaseColumns {
        public static final String TABLE_NAME = "candidate";
        public static final String _ID = "id";
        public static final String COLUMN_FIRST_NAME = "lastName";
        public static final String COLUMN_LAST_NAME = "firstName";
        public static final String COLUMN_BIRTHDAY= "birthday";
    }
}
