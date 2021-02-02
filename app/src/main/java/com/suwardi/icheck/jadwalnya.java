package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
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

import java.util.Objects;

public class jadwalnya extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myref, mDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser User;
    private ImageView photo_dok;
    private TextView nama_dok,nip_dok,spesialis_dok,nope_dok,alamat_dok,jadwal;

    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwalnya);

        jadwal = findViewById(R.id.jadwal);
        photo_dok = findViewById(R.id.photo);
        nip_dok = findViewById(R.id.no1);
        nama_dok = findViewById(R.id.nama);
        spesialis_dok = findViewById(R.id.spesialis);
        nope_dok = findViewById(R.id.no2);
        alamat_dok = findViewById(R.id.alamat);

        delete = findViewById(R.id.del_jadwal);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance() ;
        User = firebaseAuth.getCurrentUser();

        String waktu = getIntent().getStringExtra("waktu");
        String nip = getIntent().getStringExtra("ID");

        jadwal.setText(waktu);


        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Data_dokter");

        databaseReference.orderByChild("nip").startAt(nip).endAt(nip+"\uf88f").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data_dokter data_dokter = snapshot.getValue(data_dokter.class);
                Picasso.get().load(data_dokter.getUrl_photo()).into(photo_dok);
                nama_dok.setText(data_dokter.getNama());
                spesialis_dok.setText(data_dokter.getSpesialis());
                nip_dok.setText(data_dokter.getNip());
                nope_dok.setText(data_dokter.getNomer_hp());
                alamat_dok.setText(data_dokter.getAlamat());


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



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                String nip = getIntent().getStringExtra("ID");
                String idnya = getIntent().getStringExtra("ID2");
                String title = getIntent().getStringExtra("ID3");
                mDatabase.child("Data_checkup").child(nip).child(idnya).child(title).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(jadwalnya.this,"Berhasil Dihapus",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(jadwalnya.this, list_data_jadwal.class));
                    }
                });

            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(jadwalnya.this, list_data_jadwal.class));
    }
}
