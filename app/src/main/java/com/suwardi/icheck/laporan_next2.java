package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class laporan_next2 extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    TextView tv_checkup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_next2);

        firebaseDatabase = FirebaseDatabase.getInstance();
        tv_checkup = findViewById(R.id.checkup);


        String nik = getIntent().getStringExtra("nik");
        String nip = getIntent().getStringExtra("nip");


        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Data_checkup").child(nip).child(nik);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tv_checkup.setText(String.valueOf(snapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        String ID = getIntent().getStringExtra("nip");
        finish();
        Intent i = new Intent(laporan_next2.this, list_lapor.class);
        i.putExtra("ID",ID);
        startActivity(i);
    }
}
