package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class list_jadwal extends AppCompatActivity {

    CheckupAdapter checkupAdapter;
    DatabaseReference dataRef;
    ArrayList<String> idL= null;
    ArrayList<String> waktuL= null;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jadwal);
        String ID = getIntent().getStringExtra("ID");
        String NIK = getIntent().getStringExtra("nik");
        dataRef = FirebaseDatabase.getInstance().getReference().child("Data_checkup").child(ID).child(NIK);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setHasFixedSize(true);

        idL = new ArrayList<>();
        waktuL = new ArrayList<>();


        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String id = snapshot.child("idnya").getValue(String.class);
                String date = snapshot.child("date").getValue(String.class);
                String time = snapshot.child("time").getValue(String.class);
                if(id != null || date != null || time != null) {
                    idL.add(id);
                    waktuL.add(date + " , " + time);
                    checkupAdapter = new CheckupAdapter(list_jadwal.this, idL, waktuL);
                    list.setAdapter(checkupAdapter);
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




    @Override
    public void onBackPressed() {
        String ID = getIntent().getStringExtra("ID");
        finish();
        Intent i = new Intent(list_jadwal.this, daftar_pasien_checkup.class);
        i.putExtra("ID",ID);
        startActivity(i);
    }
}