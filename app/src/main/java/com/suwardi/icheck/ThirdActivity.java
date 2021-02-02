package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ThirdActivity extends AppCompatActivity {
    private Button login;
    private EditText uname,pwd;
    private FirebaseDatabase firebaseDatabase;
    private CheckBox show_pw;
    private ProgressBar load;
    private FrameLayout konten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

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
                DatabaseReference myRef = firebaseDatabase.getReference().child("Data_dokter");
                final String username = uname.getText().toString().trim();
                final String pass = pwd.getText().toString().trim();

                myRef.orderByChild("nip").startAt(username).endAt(username+"\uf88f").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String pw = snapshot.child("password").getValue(String.class);
                        if(pass.equals(pw)){

                            finish();
                            Intent i = new Intent(ThirdActivity.this, MenuDokter.class);
                            i.putExtra("ID",username);
                            startActivity(i);

                        }else{
                            konten.setVisibility(View.VISIBLE);
                            load.setVisibility(View.GONE);
                            Toast.makeText(ThirdActivity.this,"Nip atau password salah!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

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
        startActivity(new Intent(ThirdActivity.this, opsi.class));
    }
}
