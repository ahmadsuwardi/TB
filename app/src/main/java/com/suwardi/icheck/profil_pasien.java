package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class profil_pasien extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser User;
    ImageView iv_photo;
    TextView tv_nama,tv_umur,tv_nope,tv_nope_wali,tv_tgl,tv_alamat,tv_nik,tv_bb,tv_rp,tv_jk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_pasien);

        firebaseAuth = FirebaseAuth.getInstance() ;
        firebaseDatabase = FirebaseDatabase.getInstance();
        User = firebaseAuth.getCurrentUser();

        iv_photo = findViewById(R.id.photo);
        tv_nik = findViewById(R.id.nik);
        tv_bb = findViewById(R.id.bb);
        tv_rp = findViewById(R.id.rp);
        tv_jk = findViewById(R.id.jk);
        tv_nama = findViewById(R.id.nama);
        tv_umur = findViewById(R.id.umur);
        tv_nope = findViewById(R.id.nope);
        tv_nope_wali = findViewById(R.id.nope_wali);
        tv_tgl = findViewById(R.id.tgl_lahir);
        tv_alamat = findViewById(R.id.alamat);


        showData();

    }
    private void showData(){

        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Data_pasien");
        final String ID = User.getEmail();
        databaseReference.orderByChild("email").startAt(ID).endAt(ID+"\uf88f").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data_pasien data_pasien = snapshot.getValue(data_pasien.class);
                Picasso.get().load(data_pasien.getUrl_photo()).into(iv_photo);
                tv_nik.setText(data_pasien.getNik());
                tv_bb.setText(data_pasien.getBerat_badan()+" Kg");
                tv_jk.setText(data_pasien.getJenis_kelamin());
                tv_rp.setText(data_pasien.getRiwayat_penyakit());
                tv_nama.setText(data_pasien.getNama());
                tv_umur.setText(data_pasien.getUmur()+" Tahun");
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
        startActivity(new Intent(profil_pasien.this, MenuUtama.class));
    }
}
