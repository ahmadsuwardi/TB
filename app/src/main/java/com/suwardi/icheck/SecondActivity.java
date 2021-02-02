package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;

public class SecondActivity extends AppCompatActivity {

    private Button login;
    private EditText uname,pwd;
    private FirebaseDatabase firebaseDatabase;
    private CheckBox show_pw;
    private ProgressBar load;
    private FrameLayout konten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        login = findViewById(R.id.login);
        uname = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        load = findViewById(R.id.load);
        konten = findViewById(R.id.konten);
        show_pw = findViewById(R.id.show);
        firebaseDatabase = FirebaseDatabase.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konten.setVisibility(View.GONE);
                load.setVisibility(View.VISIBLE);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference().child("admin");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String username = uname.getText().toString().trim();
                        String pass = pwd.getText().toString().trim();
                        log_admin log_admin = dataSnapshot.getValue(log_admin.class);
                        assert log_admin != null;
                        if(username.equals(log_admin.getUsername()) && pass.equals(log_admin.getPassword())){
                            finish();
                            Intent i = new Intent(SecondActivity.this, MenuAdmin.class);
                            startActivity(i);
                        }else{
                            konten.setVisibility(View.VISIBLE);
                            load.setVisibility(View.GONE);
                            Toast.makeText(SecondActivity.this,"Username atau password salah!",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(SecondActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        show_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_pw.isChecked()){pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());}
                else{pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());}
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(SecondActivity.this, opsi.class));
    }
}
