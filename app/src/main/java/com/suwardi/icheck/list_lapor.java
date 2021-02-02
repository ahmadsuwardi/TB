package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class list_lapor extends AppCompatActivity {

    AdapterLaporan adapterLaporan;
    DatabaseReference dataRef;
    ArrayList<String> photoL=null;
    ArrayList<String> nipL=null;
    ArrayList<String> idL=null;
    ArrayList<String> namaL=null;
    ArrayList<String> umurL=null;
    ArrayList<String> nopeL=null;
    ArrayList<String> nope_waliL=null;
    ArrayList<String> tgl_lahirL=null;
    ArrayList<String> alamatL=null;

    ArrayList<String> obat=null;
    ArrayList<String> checkup=null;
    ArrayList<String> send=null;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lapor);

        String ID = getIntent().getStringExtra("ID");

        dataRef = FirebaseDatabase.getInstance().getReference().child("Data_pasien");
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setHasFixedSize(true);

        photoL = new ArrayList<>();
        nipL = new ArrayList<>();
        idL = new ArrayList<>();
        namaL=new ArrayList<>();
        umurL=new ArrayList<>();
        nopeL=new ArrayList<>();
        nope_waliL=new ArrayList<>();
        tgl_lahirL=new ArrayList<>();
        alamatL=new ArrayList<>();
        obat=new ArrayList<>();
        checkup=new ArrayList<>();
        send=new ArrayList<>();

        dataRef.orderByChild("nip_dokter").equalTo(ID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String photo = snapshot.child("url_photo").getValue(String.class);
                String nip = snapshot.child("nip_dokter").getValue(String.class);
                String id = snapshot.child("nik").getValue(String.class);
                String nama = snapshot.child("nama").getValue(String.class);
                String umur = snapshot.child("umur").getValue(String.class);
                String nope = snapshot.child("nomer_hp").getValue(String.class);
                String nope_wali = snapshot.child("nomer_hp_wali").getValue(String.class);
                String tgl_lahir = snapshot.child("tanggal_lahir").getValue(String.class);
                String alamat = snapshot.child("alamat").getValue(String.class);

                photoL.add(photo);
                nipL.add(nip);
                idL.add(id);
                namaL.add(nama);
                umurL.add(umur);
                nopeL.add(nope);
                nope_waliL.add(nope_wali);
                tgl_lahirL.add(tgl_lahir);
                alamatL.add(alamat);

                obat.add(id);
                checkup.add(id);
                send.add(id);

                adapterLaporan = new AdapterLaporan(list_lapor.this,  photoL,nipL,idL, namaL, umurL, nopeL, nope_waliL, tgl_lahirL,  alamatL, obat, checkup,  send);
                list.setAdapter(adapterLaporan);
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
        Intent i = new Intent(list_lapor.this, MenuDokter.class);
        i.putExtra("ID",ID);
        startActivity(i);
    }
}
