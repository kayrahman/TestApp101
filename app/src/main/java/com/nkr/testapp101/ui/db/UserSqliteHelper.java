package com.nkr.testapp101.ui.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by neel on 07/09/2016.
 */
public class UserSqliteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="test.db";
    public static final int DB_VERSION=1;

    public static final String USER_TABLE="users";

    public static final String COLUMN_FIRSTNAME="FIRST_NAME";
    public static final String COLUMN_LASTNAME="LAST_NAME";
    public static final String COLUMN_EMAIL="EMAIL";
    public static final String COLUMN_PW="PASSWORD";
    public static final String COLUMN_PHONE="PHONE";
    public static final String COLUMN_USERTYPE="USER_TYPE";

    public static final String CREATE_USER=
            "CREATE TABLE users(ID INTEGER PRIMARY KEY,"+COLUMN_FIRSTNAME+" TEXT,"+COLUMN_LASTNAME+" TEXT,"+COLUMN_EMAIL+" TEXT NOT NULL," +
                    ""+COLUMN_PW+" TEXT NOT NULL,"+COLUMN_PHONE+" TEXT NOT NULL,"+COLUMN_USERTYPE+" TEXT NOT NULL)";


    public UserSqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
