package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class reg extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email,password;
    private CheckBox show_pw;
    private Button daftar;
    private ProgressBar load;
    private FrameLayout konten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        mAuth = FirebaseAuth.getInstance();
        load = findViewById(R.id.load);
        konten = findViewById(R.id.konten);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        show_pw = findViewById(R.id.show);
        daftar = findViewById(R.id.daftar);

        show_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_pw.isChecked()){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());}
                else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());}}
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validasi()){
                    String user_email = email.getText().toString().trim();
                    String user_pw = password.getText().toString().trim();
                    mAuth.createUserWithEmailAndPassword(user_email,user_pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());
                            if(!task.isSuccessful()){
                                task.getException();

                            }else{
                                Toast.makeText(reg.this, "Silakan cek email anda !", Toast.LENGTH_SHORT).show();
                                sendVerificationEmail();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean validasi(){
        boolean result = false;

        konten.setVisibility(View.GONE);
        load.setVisibility(View.VISIBLE);
        String mail = email.getText().toString();
        String pw = password.getText().toString();
        if(mail.isEmpty() || pw.isEmpty()) {
            Toast.makeText(this, "Mohon Periksa Kolom Yang Tersedia!", Toast.LENGTH_SHORT).show();
            konten.setVisibility(View.VISIBLE);
            load.setVisibility(View.GONE);
        }else{
            result = true;
        }
        return result;
    }

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            // email sent
                            // after email is sent just logout the user and finish this activity
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(reg.this, MainActivity.class));
                            finish();
                        }
                        else
                        {
                            // email not sent, so display message and restart the activity or do whatever you wish to do
                            Toast.makeText(reg.this,"Email verifikasi gagal dikirim, mohon isi dengan email yang valid!", Toast.LENGTH_SHORT).show();
                            //restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(reg.this, MainActivity.class));
    }
}
