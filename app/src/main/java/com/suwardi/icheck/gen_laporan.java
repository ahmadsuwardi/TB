package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class gen_laporan extends AppCompatActivity {


    private FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser User;
    ImageView iv_photo;
    TextView tv_nama,tv_umur,tv_nope,tv_nope_wali,tv_tgl,tv_alamat,tv_total,tv_jadwal,tv_checkup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_laporan);

        firebaseAuth = FirebaseAuth.getInstance() ;
        firebaseDatabase = FirebaseDatabase.getInstance();
        User = firebaseAuth.getCurrentUser();

        iv_photo = findViewById(R.id.photo);
        tv_nama = findViewById(R.id.nama);
        tv_umur = findViewById(R.id.umur);
        tv_nope = findViewById(R.id.nope);
        tv_nope_wali = findViewById(R.id.nope_wali);
        tv_tgl = findViewById(R.id.tgl_lahir);
        tv_alamat = findViewById(R.id.alamat);
        tv_total = findViewById(R.id.total);
        tv_jadwal = findViewById(R.id.jadwal);
        tv_checkup = findViewById(R.id.checkup);


        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Data_pasien");
        final String ID = User.getEmail();
        databaseReference.orderByChild("email").startAt(ID).endAt(ID+"\uf88f").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                final data_pasien data_pasien = snapshot.getValue(data_pasien.class);
                    Picasso.get().load(data_pasien.getUrl_photo()).into(iv_photo);
                    tv_nama.setText(data_pasien.getNama());
                    tv_umur.setText(data_pasien.getUmur() + " Tahun");
                    tv_nope.setText(data_pasien.getNomer_hp());
                    tv_nope_wali.setText(data_pasien.getNomer_hp_wali());
                    tv_tgl.setText(data_pasien.getTanggal_lahir());
                    tv_alamat.setText(data_pasien.getAlamat());

                    final String nik = data_pasien.getNik();
                    final String nip = data_pasien.getNip_dokter();

                    final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                    final DatabaseReference myref = mDatabase.child("Data_minum_obat").child(nip).child(nik);

                    myref.child("laporan").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                String total = snapshot.child("total").getValue().toString();
                                tv_total.setText(total);
                            }else{
                                HashMap hashMap = new HashMap();
                                hashMap.put("total", "0");
                                myref.child("laporan").setValue(hashMap);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    myref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                Long jumlah_jadwal = snapshot.getChildrenCount() - 1;
                                tv_jadwal.setText(String.valueOf(jumlah_jadwal));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                mDatabase.child("Data_checkup").child(nip).child(nik).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                        tv_checkup.setText(String.valueOf(snapshot.getChildrenCount()));}
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
        startActivity(new Intent(gen_laporan.this, MenuUtama.class));
    }

}
