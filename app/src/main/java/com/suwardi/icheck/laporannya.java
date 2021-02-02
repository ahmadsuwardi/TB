package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class laporannya extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    ImageView iv_photo;
    TextView tv_nama,tv_umur,tv_nope,tv_nope_wali,tv_tgl,tv_alamat,tv_total,tv_jadwal,tv_checkup,jadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporannya);

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
        jadwal = findViewById(R.id.waktu);

        firebaseDatabase = FirebaseDatabase.getInstance();
        String uid = getIntent().getStringExtra("ID");
        String waktu = getIntent().getStringExtra("waktu");

        jadwal.setText(waktu);

        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Data_pasien");

        databaseReference.orderByChild("nomer_hp_wali").startAt(uid).endAt(uid+"\uf88f").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {final data_pasien data_pasien = snapshot.getValue(data_pasien.class);
                Picasso.get().load(data_pasien.getUrl_photo()).into(iv_photo);
                tv_nama.setText(data_pasien.getNama());
                tv_umur.setText(data_pasien.getUmur() + " Tahun");
                tv_nope.setText(data_pasien.getNomer_hp());
                tv_nope_wali.setText(data_pasien.getNomer_hp_wali());
                tv_tgl.setText(data_pasien.getTanggal_lahir());
                tv_alamat.setText(data_pasien.getAlamat());

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
        startActivity(new Intent(laporannya.this, MenuDokter.class));
    }

}

