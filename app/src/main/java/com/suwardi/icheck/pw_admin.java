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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class pw_admin extends AppCompatActivity {

    private Button reset;
    private EditText pwd,pwd2;
    private FirebaseDatabase firebaseDatabase;
    private CheckBox show_pw;
    private ProgressBar load;
    private FrameLayout konten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_admin);

        reset = findViewById(R.id.ganti);
        pwd = findViewById(R.id.password);
        pwd2 = findViewById(R.id.new_password);
        load = findViewById(R.id.load);
        konten = findViewById(R.id.konten);
        show_pw = findViewById(R.id.show);
        firebaseDatabase = FirebaseDatabase.getInstance();

        show_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_pw.isChecked()){pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pwd2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());}
                else{pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());}
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konten.setVisibility(View.GONE);
                load.setVisibility(View.VISIBLE);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = firebaseDatabase.getReference().child("admin");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        log_admin log_admin = snapshot.getValue(log_admin.class);
                        String pass = pwd.getText().toString().trim();
                        String pw = log_admin.getPassword().trim();
                        String pass2 = pwd2.getText().toString().trim();
                        String uname = log_admin.getUsername().trim();
                        if(pass.equals(pw)){
                            log_admin log_admin1 = new log_admin(uname,pass2);
                            myRef.setValue(log_admin1);
                            Toast.makeText(pw_admin.this, "Password berhasil diubah, silakan login ulang!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(pw_admin.this, SecondActivity.class));
                            finish();
                        }else{
                            Toast.makeText(pw_admin.this, "Password gagal diubah, silakan login ulang!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(pw_admin.this, SecondActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(pw_admin.this, "Data gagal dimuat!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(pw_admin.this, MenuAdmin.class));
    }
}
