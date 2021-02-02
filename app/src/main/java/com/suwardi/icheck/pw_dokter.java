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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class pw_dokter extends AppCompatActivity {

    private Button reset;
    private EditText pwd,pwd2;
    private FirebaseDatabase firebaseDatabase;
    private CheckBox show_pw;
    private ProgressBar load;
    private FrameLayout konten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_dokter);

        final String ID = getIntent().getStringExtra("ID");

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
                final DatabaseReference myRef = firebaseDatabase.getReference().child("Data_dokter");
                myRef.orderByChild("nip").startAt(ID).endAt(ID+"\uf88f").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        String pass = pwd.getText().toString().trim();
                        String pass2 = pwd2.getText().toString().trim();
                        String pw = snapshot.child("password").getValue(String.class);

                            if(pass.equals(pw)){
                                HashMap hashMap = new HashMap();
                                hashMap.put("nip",snapshot.child("nip").getValue(String.class));
                                hashMap.put("password",pass2);
                                hashMap.put("nama",snapshot.child("nama").getValue(String.class));
                                hashMap.put("spesialis",snapshot.child("spesialis").getValue(String.class));
                                hashMap.put("nomer_hp",snapshot.child("nomer_hp").getValue(String.class));
                                hashMap.put("alamat",snapshot.child("alamat").getValue(String.class));
                                hashMap.put("url_photo",snapshot.child("url_photo").getValue(String.class));
                                myRef.child(snapshot.child("nama").getValue(String.class)).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(pw_dokter.this, "Password berhasil diubah, silakan login ulang!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(pw_dokter.this, ThirdActivity.class));
                                        finish();
                                    }
                                });

                            }else{
                                Toast.makeText(pw_dokter.this, "Password gagal diubah, silakan login ulang!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(pw_dokter.this, ThirdActivity.class));
                                finish();
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
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(pw_dokter.this, MenuDokter.class));
    }
}
