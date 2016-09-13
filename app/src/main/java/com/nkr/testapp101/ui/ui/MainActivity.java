package com.nkr.testapp101.ui.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nkr.testapp101.R;
import com.nkr.testapp101.ui.Fragments.LoginFragment;
import com.nkr.testapp101.ui.Fragments.ViewPagerFragment;
import com.nkr.testapp101.ui.UserSharedPref;
import com.nkr.testapp101.ui.db.UserDataSource;
import com.nkr.testapp101.ui.model.User;

public class MainActivity extends AppCompatActivity {

   private UserSharedPref pref;

    private TextView mUserName;
    private EditText mMobileNum;
    private Button mEditBtn;
    private Button mUserTypeBtn;

    private ProgressDialog mProgressDialog;


    User user=new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        //UserDataSource dataSource=new UserDataSource(getApplicationContext());


        pref=new UserSharedPref(this);

        if(!pref.loggedIn()){
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        mUserName=(TextView)findViewById(R.id.mainUserName);
        mEditBtn=(Button)findViewById(R.id.editMobBtn);
        mUserTypeBtn=(Button)findViewById(R.id.userTypeButton);
        mMobileNum=(EditText)findViewById(R.id.mobEditText);
        mProgressDialog=new ProgressDialog(MainActivity.this);


//



        String userName="";
        String mobile="";
         String userType="";

        Intent intent=getIntent();
        Bundle b=intent.getExtras();
        if(b!=null){
            userName=b.getString("UserName");
            mobile=b.getString("MobileNum");
          userType=b.getString("UserType");

            mUserName.setText(userName);
            mMobileNum.setText(mobile);
            final String finalUserType = userType;
            mUserTypeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,"Your account type is "+ finalUserType,Toast.LENGTH_LONG).show();
                }
            });
        }

        final String finalUserName = userName;
        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMobileNum(finalUserName);
            }
        });

    }

    private void editMobileNum(String uName) {
        String mobNum=mMobileNum.getText().toString();

        if(!TextUtils.isEmpty(mobNum)){
            UserDataSource datasource=new UserDataSource(getApplicationContext());
           // datasource.updateMobileNum(uName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            mProgressDialog.setMessage("Logging out");
            mProgressDialog.show();

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    pref.setLoggedIn(false);

                    Intent intent=new Intent(MainActivity.this,FlashScreenActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                    mProgressDialog.dismiss();
                    finish();
                }
            },5000);





        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        UserDataSource dataSource=new UserDataSource(getApplicationContext());
//        dataSource.open();

    }

    @Override
    protected void onPause() {
        super.onPause();
//        UserDataSource dataSource=new UserDataSource(getApplicationContext());
//        dataSource.close();
    }
}
