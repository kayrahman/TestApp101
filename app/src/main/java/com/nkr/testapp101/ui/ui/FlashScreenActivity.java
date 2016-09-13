package com.nkr.testapp101.ui.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nkr.testapp101.R;

public class FlashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);


        ImageView img=(ImageView)findViewById(R.id.splashImage) ;

        Animation anim= AnimationUtils.loadAnimation(this,R.anim.anim_down);
        anim.start();


        Handler handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(FlashScreenActivity.this,MainActivity.class));
                finish();

            }
        },5000);



    }
}
