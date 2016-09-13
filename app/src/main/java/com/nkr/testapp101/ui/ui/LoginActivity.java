package com.nkr.testapp101.ui.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nkr.testapp101.R;
import com.nkr.testapp101.ui.Fragments.ViewPagerFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ViewPagerFragment viewPager=new ViewPagerFragment();
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.placeHolder,viewPager);
        // transaction.addToBackStack(null);
        transaction.commit();
    }
}
