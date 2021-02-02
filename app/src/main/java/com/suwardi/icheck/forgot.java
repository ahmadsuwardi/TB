package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot extends AppCompatActivity {

    private ProgressBar load;
    private FrameLayout konten;
    private EditText email;
    private Button reset;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);


        auth = FirebaseAuth.getInstance();
        load = findViewById(R.id.load);
        konten = findViewById(R.id.konten);

        email = findViewById(R.id.email);
        reset = findViewById(R.id.forgot);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validasi()){
                    String send_email = email.getText().toString().trim();

                    auth.sendPasswordResetEmail(send_email)

                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(forgot.this, "Tautan reset password terkirim, cek email anda!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(forgot.this, MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(forgot.this, "Gagal mengirim tautan reset password!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }else{
                    konten.setVisibility(View.VISIBLE);
                    load.setVisibility(View.GONE);
                }
            }
        });

    }
    private boolean validasi(){
        boolean result = false;
        konten.setVisibility(View.GONE);
        load.setVisibility(View.VISIBLE);
        String mail = email.getText().toString();

        if(mail.isEmpty()){
            Toast.makeText(this,"Mohon Periksa Kolom Yang Tersedia!", Toast.LENGTH_SHORT).show();

        }else{
            result = true;
        }
        return result;
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(forgot.this, MainActivity.class));
    }
}
