package com.suwardi.icheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash extends AppCompatActivity {
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);
        Animation transisi = AnimationUtils.loadAnimation(this, R.anim.transisi);
        logo.startAnimation(transisi);
        final Intent i = new Intent(this,opsi.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(1500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }

    @Override
    public void onBackPressed() {
    }
}