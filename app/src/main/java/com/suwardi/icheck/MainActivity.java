package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser User;
    private ProgressBar load;
    private FrameLayout konten;
    private Button forgot,login;
    private TextView register;
    private EditText email,password;
    private CheckBox show_pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load = findViewById(R.id.load);
        konten = findViewById(R.id.konten);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        show_pw = findViewById(R.id.show);
        forgot = findViewById(R.id.forgot);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        firebaseAuth = FirebaseAuth.getInstance() ;
        User = firebaseAuth.getCurrentUser();

        if(User != null){
            checkIfEmailVerified();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasi();
            }
        });

        show_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_pw.isChecked()){password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());}
                else{password.setTransformationMethod(PasswordTransformationMethod.getInstance());}
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, reg.class));
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, forgot.class));
            }
        });
    }
    private void validasi() {
        String mail = email.getText().toString().trim();
        String pw = password.getText().toString().trim();
        if (mail.isEmpty() || pw.isEmpty()) {
            Toast.makeText(MainActivity.this, "Mohon isi form terlebih dahulu!", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(mail, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Email/password salah!", Toast.LENGTH_SHORT).show();
                                //Log.w("TAG", "signInWithEmail:failed", task.getException());

                            } else {
                                checkIfEmailVerified();
                            }

                        }
                    });
        }
    }

    private void checkIfEmailVerified() {
        konten.setVisibility(View.GONE);
        load.setVisibility(View.VISIBLE);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            finish();
            startActivity(new Intent(MainActivity.this, MenuUtama.class));
        }
        else
        {
            konten.setVisibility(View.VISIBLE);
            load.setVisibility(View.GONE);
            // email is not verified, so just Prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            finish();
            Intent i = new Intent(MainActivity.this, cek_email.class);
            String emailnya = email.getText().toString().trim();
            i.putExtra("emailnya",emailnya);
            startActivity(i);
            //restart this activity

        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(MainActivity.this, opsi.class));
    }
}
