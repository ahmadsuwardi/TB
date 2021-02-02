package com.suwardi.icheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class notif_checkup extends AppCompatActivity {
    TextView msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_checkup);
        msg = findViewById(R.id.msg);
        Animation transisi = AnimationUtils.loadAnimation(this, R.anim.transisi);
        msg.startAnimation(transisi);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
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