package com.nkr.testapp101.ui.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.nkr.testapp101.ui.model.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by neel on 07/09/2016.
 */
public class UserDataSource {

    private SQLiteDatabase mDatabase;
    private UserSqliteHelper mUserHelper;
    private Context mContext;

    public UserDataSource(Context context) {
        mContext = context;
        mUserHelper=new UserSqliteHelper(mContext);
    }

    public void open(){
        mDatabase=mUserHelper.getWritableDatabase();
    }

    public void close(){
        mDatabase.close();
    }

    public void insertUserData(User user){
        open();
        mDatabase.beginTransaction();

        try {
            ContentValues cv = new ContentValues();

            cv.put(UserSqliteHelper.COLUMN_FIRSTNAME, user.getfName());
            cv.put(UserSqliteHelper.COLUMN_LASTNAME, user.getlName());
            cv.put(UserSqliteHelper.COLUMN_EMAIL, user.getEmail());
            cv.put(UserSqliteHelper.COLUMN_PW, user.getPassword());
            cv.put(UserSqliteHelper.COLUMN_PHONE, user.getPhone());
            cv.put(UserSqliteHelper.COLUMN_USERTYPE, user.getUserType());


            mDatabase.insert(UserSqliteHelper.USER_TABLE, null, cv);
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
            close();

        }catch (SQLiteException e){

        }


    }

    public String searchedPassword(String uEmail){
        open();
        mDatabase=mUserHelper.getReadableDatabase();

        String query="select "+mUserHelper.COLUMN_EMAIL+","+mUserHelper.COLUMN_PW+" from "+mUserHelper.USER_TABLE;

        Cursor cursor=mDatabase.rawQuery(query,null);

        String email="";
        String password="";

        password="Not found";

        if(cursor.moveToFirst()){
            do{
                email=cursor.getString(0);
                if(email.equals(uEmail)){
                    password=cursor.getString(1);
                    break;
                }

            }while(cursor.moveToNext());
        }

        close();

        return password;



    }

    public boolean isEmailExists(String uEmail){
        open();
        mDatabase=mUserHelper.getReadableDatabase();

        String query="select "+mUserHelper.COLUMN_EMAIL+" from "+mUserHelper.USER_TABLE;

        Cursor cursor=mDatabase.rawQuery(query,null);

        boolean emailExists=false;
        String email="";


        if(cursor.moveToFirst()){
            do{
                email=cursor.getString(0);
                if(email.equals(uEmail)){
                    emailExists=true;
                    break;
                }

            }while(cursor.moveToNext());
        }

        return emailExists;

    }


    public User readUser(String uEmail){
       open();

        String query="select * from "+mUserHelper.USER_TABLE+" where "+mUserHelper.COLUMN_PW+"= "+uEmail;


    Cursor  cursor=mDatabase.rawQuery(

            query, null);


        User user=new User();

        if(cursor.moveToFirst()) {
            do {
               User user1 = new User();
                user1.setfName(cursor.getString(1));
                user1.setlName(cursor.getString(2));
                user1.setEmail(cursor.getString(3));
                user1.setPassword(cursor.getString(4));
                user1.setPhone(cursor.getString(5));
                user1.setUserType(cursor.getString(6));

                user=user1;

            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return user;

    }


//  public void updateMobileNum(String user){
//      open();
//      mDatabase.beginTransaction();
//
//      try {
//          ContentValues cv = new ContentValues();
//
//          cv.put(UserSqliteHelper.COLUMN_PHONE, user.getPhone());
//
//
//
//          mDatabase.update(UserSqliteHelper.USER_TABLE, cv,null);
//          mDatabase.setTransactionSuccessful();
//          mDatabase.endTransaction();
//          close();
//
//      }catch (SQLiteException e){
//
//      }
//

//  }


}
