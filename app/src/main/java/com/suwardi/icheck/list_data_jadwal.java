package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class list_data_jadwal extends AppCompatActivity {

    SearchAdapter searchAdapter;
    DatabaseReference DataRef,DataRef2;
    private DatabaseReference mDatabase;
    RecyclerView recyclerView;

    FirebaseAuth firebaseAuth;
    FirebaseUser User;
    private Button tambah;

    ArrayList<String> waktuL= null;
    ArrayList<String> nipL= null;
    ArrayList<String> idL= null;
    ArrayList<String> uidL= null;
    ArrayList<String> namaL= null;
    ArrayList<String> spesialisL= null;
    ArrayList<String> nopeL= null;
    ArrayList<String> alamatL= null;
    ArrayList<String> urlL= null;
    ArrayList<String> keyL= null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_jadwal);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        waktuL = new ArrayList<>();
        nipL = new ArrayList<>();
        idL = new ArrayList<>();
        uidL = new ArrayList<>();
        namaL = new ArrayList<>();
        spesialisL = new ArrayList<>();
        nopeL = new ArrayList<>();
        alamatL = new ArrayList<>();
        urlL = new ArrayList<>();
        keyL = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance() ;
        User = firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference = mDatabase.child("Data_pasien");
        final String ID = User.getEmail();
        DataRef = FirebaseDatabase.getInstance().getReference().child("Data_dokter");

        tambah = findViewById(R.id.add_checkup);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(list_data_jadwal.this, upload_jadwal_checkup.class));
            }
        });
        databaseReference.orderByChild("email").startAt(ID).endAt(ID+"\uf88f").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data_pasien data_pasien = snapshot.getValue(data_pasien.class);
                final String nip = data_pasien.getNip_dokter();
                final String uid = data_pasien.getNik();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                DataRef2 = mDatabase.child("Data_checkup").child(nip).child(uid);
                DataRef2.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String date = snapshot.child("date").getValue(String.class);
                        String time = snapshot.child("time").getValue(String.class);
                        String id = snapshot.child("idnya").getValue(String.class);
                        String waktu = date+ ", " + time;
                        waktuL.add(waktu);
                        nipL.add(nip);
                        idL.add(id);
                        uidL.add(uid);
                        searchAdapter = new SearchAdapter(list_data_jadwal.this,waktuL,nipL ,idL,uidL);
                        recyclerView.setAdapter(searchAdapter);
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
        startActivity(new Intent(list_data_jadwal.this, MenuUtama.class));
    }
}
