package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class laporan_next extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    TextView tv_total,tv_jadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_next);

        firebaseDatabase = FirebaseDatabase.getInstance();
        tv_jadwal = findViewById(R.id.jadwal);
        tv_total = findViewById(R.id.total);

        String nik = getIntent().getStringExtra("nik");
        String nip = getIntent().getStringExtra("nip");


        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Data_minum_obat").child(nip).child(nik);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getChildrenCount() == 1) {
                    tv_jadwal.setText("0");
                }else if(snapshot.getChildrenCount() > 1){
                    Long jumlah_jadwal = snapshot.getChildrenCount()-1;
                    tv_jadwal.setText(String.valueOf(jumlah_jadwal));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child("laporan").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String total = snapshot.child("total").getValue().toString();
                tv_total.setText(total);

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
        Intent i = new Intent(laporan_next.this, list_lapor.class);
        i.putExtra("ID",ID);
        startActivity(i);
    }
}
