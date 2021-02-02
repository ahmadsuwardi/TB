package com.suwardi.icheck;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class tu_list_jadwal_checkup extends AppCompatActivity {
    ObatAdapter obatAdapter;
    DatabaseReference dataRef,dataRef1;
    ArrayList<String> idL = null;
    ArrayList<String> waktuL = null;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jadwal);

        dataRef = FirebaseDatabase.getInstance().getReference().child("Data_checkup");
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setHasFixedSize(true);

        idL = new ArrayList<>();
        waktuL = new ArrayList<>();

        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                dataRef.child(snapshot.getKey()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        for (DataSnapshot snapshot2 : snapshot.getChildren()){

                            String id = snapshot2.child("idnya").getValue(String.class);
                            String date = snapshot2.child("date").getValue(String.class);
                            String time = snapshot2.child("time").getValue(String.class);

                            if(id != null || date != null || time != null) {
                                idL.add(id);
                                waktuL.add(date + " , " + time);
                                obatAdapter = new ObatAdapter(tu_list_jadwal_checkup.this, idL, waktuL);
                                list.setAdapter(obatAdapter);
                            }
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
        finish();
        Intent i = new Intent(tu_list_jadwal_checkup.this, opsi_data_jadwal.class);
        startActivity(i);
    }
}
