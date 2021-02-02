package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class list_data_pasien extends AppCompatActivity {

    DatabaseReference dataRef;
    RecyclerView list;
    FirebaseRecyclerOptions<data_pasien> options;
    FirebaseRecyclerAdapter<data_pasien,holder_pasien> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_pasien);

        list = findViewById(R.id.list);

        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setHasFixedSize(true);


        loadData();

    }

    private void loadData() {
        dataRef = FirebaseDatabase.getInstance().getReference().child("Data_pasien");
        Query query = dataRef;
        options = new FirebaseRecyclerOptions.Builder<data_pasien>().setQuery(query,data_pasien.class).build();
        adapter = new FirebaseRecyclerAdapter<data_pasien, holder_pasien>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final holder_pasien myViewHolder, final int i, @NonNull final data_pasien data_pasien) {
                Picasso.get().load(data_pasien.getUrl_photo()).into(myViewHolder.iv_photo);
                myViewHolder.tv_nama.setText(data_pasien.getNama());
                myViewHolder.tv_nik.setText(data_pasien.getNik());
                myViewHolder.tv_bb.setText(data_pasien.getBerat_badan()+" Kg");
                myViewHolder.tv_jk.setText(data_pasien.getJenis_kelamin());
                myViewHolder.tv_rp.setText(data_pasien.getRiwayat_penyakit());
                myViewHolder.tv_umur.setText(data_pasien.getUmur()+" Tahun");
                myViewHolder.tv_nope.setText(data_pasien.getNomer_hp());
                myViewHolder.tv_nope_wali.setText(data_pasien.getNomer_hp_wali());
                myViewHolder.tv_tgl.setText(data_pasien.getTanggal_lahir());
                myViewHolder.tv_alamat.setText(data_pasien.getAlamat());

            }

            @NonNull
            @Override
            public holder_pasien onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewlist_pasien,parent,false);
                return new holder_pasien(v);
            }
        };
        adapter.startListening();
        list.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(list_data_pasien.this, MenuAdmin.class));
    }
}
