package com.suwardi.icheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class cek_email extends AppCompatActivity {
    ///pembuatan variabel//
    FirebaseAuth firebaseAuth;
    FirebaseUser User;
    ProgressBar load;
    FrameLayout konten,konten2;
    //////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_email);

        ///inisiasi variabel////
        firebaseAuth = FirebaseAuth.getInstance() ;
        User = firebaseAuth.getCurrentUser();
        ////////////////////

        ////pembuatan & inisiasi variabel////////
        String mail = getIntent().getStringExtra("emailnya");
        final TextView emailnya = (TextView)findViewById(R.id.email);
        /////////////////////////////////////
        // logika validasi email //////
        if(mail.equals("")){
            emailnya.setText(User.getEmail().toString().trim());}
        else{ emailnya.setText(mail);}
        /////////////
        Button verifikasi = (Button) findViewById(R.id.verifikasi);
        verifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load = findViewById(R.id.load);
                load.setVisibility(View.VISIBLE);

                konten = findViewById(R.id.konten);
                konten.setVisibility(View.GONE);

                konten2 = findViewById(R.id.konten2);
                konten2.setVisibility(View.GONE);


                User.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(cek_email.this, "Email Verifikasi Terkirim!", Toast.LENGTH_SHORT).show();
                        load.setVisibility(View.INVISIBLE);
                        konten.setVisibility(View.VISIBLE);
                        konten2.setVisibility(View.VISIBLE);

                    }
                });
            }
        });


        Button reload = (Button)findViewById(R.id.reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.reload();
                if (User.isEmailVerified())
                {
                    // user is verified, so you can finish this activity or send user to activity which you want.
                    finish();
                    startActivity(new Intent(cek_email.this, MenuUtama.class));
                }
            }
        });

        Button keluar = (Button)findViewById(R.id.logout);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(cek_email.this, MainActivity.class));

            }
        });

    }
}
