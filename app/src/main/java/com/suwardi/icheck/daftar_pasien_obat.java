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

public class daftar_pasien_obat extends AppCompatActivity {

    PasienAdapter2 pasienAdapter;
    DatabaseReference dataRef,pasRef;

    ArrayList<String> namaL= null;
    ArrayList<String> idL= null;
    ArrayList<String> nipL= null;
    ArrayList<String> umurL= null;
    ArrayList<String> nopeL= null;
    ArrayList<String> nope2L= null;
    ArrayList<String> alamatL= null;
    ArrayList<String> urlL= null;
    ArrayList<String> tanggalL= null;
    ArrayList<String> jkL = null ;
    ArrayList<String> bbL = null ;
    ArrayList<String> rpL = null ;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pasien_obat);

        String ID = getIntent().getStringExtra("ID");
        dataRef = FirebaseDatabase.getInstance().getReference().child("Data_minum_obat").child(ID);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setHasFixedSize(true);
        namaL = new ArrayList<>();
        idL = new ArrayList<>();
        nipL = new ArrayList<>();
        umurL = new ArrayList<>();
        tanggalL = new ArrayList<>();
        alamatL = new ArrayList<>();
        urlL = new ArrayList<>();
        nopeL = new ArrayList<>();
        nope2L = new ArrayList<>();
        jkL = new ArrayList<>();
        bbL = new ArrayList<>();
        rpL = new ArrayList<>();

        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                dataRef.child(Objects.requireNonNull(snapshot.getKey())).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        pasRef = FirebaseDatabase.getInstance().getReference().child("Data_pasien");
                        Query query = pasRef.orderByChild("nik").equalTo(snapshot.getKey());

                        query.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                String url = snapshot.child("url_photo").getValue(String.class);
                                String nope = snapshot.child("nomer_hp").getValue(String.class);
                                String nope2 = snapshot.child("nomer_hp_wali").getValue(String.class);
                                String tanggal = snapshot.child("tanggal_lahir").getValue(String.class);
                                String alamat = snapshot.child("alamat").getValue(String.class);
                                String nama = snapshot.child("nama").getValue(String.class);
                                String umur = snapshot.child("umur").getValue(String.class);
                                String id = snapshot.child("nik").getValue(String.class);
                                String nip = snapshot.child("nip_dokter").getValue(String.class);
                                String jk = snapshot.child("jenis_kelamin").getValue(String.class);
                                String bb = snapshot.child("berat_badan").getValue(String.class);
                                String rp = snapshot.child("riwayat_penyakit").getValue(String.class);

                                urlL.add(url);
                                nopeL.add(nope);
                                nope2L.add(nope2);
                                tanggalL.add(tanggal);
                                alamatL.add(alamat);
                                namaL.add(nama);
                                umurL.add(umur);
                                idL.add(id);
                                nipL.add(nip);
                                jkL.add(jk);
                                bbL.add(bb);
                                rpL.add(rp);
                                pasienAdapter = new PasienAdapter2(daftar_pasien_obat.this , idL ,  urlL ,  namaL, umurL,  nopeL,  nope2L,  tanggalL,  alamatL,nipL,jkL,bbL,rpL);
                                list.setAdapter(pasienAdapter);
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
        String ID = getIntent().getStringExtra("ID");
        finish();
        Intent i = new Intent(daftar_pasien_obat.this, MenuDokter.class);
        i.putExtra("ID",ID);
        startActivity(i);
    }
}
