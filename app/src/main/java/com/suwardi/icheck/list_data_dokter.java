package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class list_data_dokter extends AppCompatActivity {


    private Button add;
    DatabaseReference dataRef;
    RecyclerView list;
    FirebaseRecyclerOptions<data_dokter> options;
    FirebaseRecyclerAdapter<data_dokter,holder_dokter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_dokter);

        add = findViewById(R.id.add_dokter);
        list = findViewById(R.id.list);

        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setHasFixedSize(true);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(list_data_dokter.this, upload_data_dokter.class));
            }
        });

        loadData();
    }

    private void loadData() {
        dataRef = FirebaseDatabase.getInstance().getReference().child("Data_dokter");
        Query query = dataRef;
        options = new FirebaseRecyclerOptions.Builder<data_dokter>().setQuery(query,data_dokter.class).build();
        adapter = new FirebaseRecyclerAdapter<data_dokter, holder_dokter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final holder_dokter myViewHolder, final int i, @NonNull final data_dokter data_dokter) {
                Picasso.get().load(data_dokter.getUrl_photo()).into(myViewHolder.iv_photo);
                myViewHolder.tv_nip.setText(data_dokter.getNip());
                myViewHolder.tv_nama.setText(data_dokter.getNama());
                myViewHolder.tv_spesialis.setText(data_dokter.getSpesialis());
                myViewHolder.tv_nope.setText(data_dokter.getNomer_hp());
                myViewHolder.tv_alamat.setText(data_dokter.getAlamat());
                myViewHolder.del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataRef.child(data_dokter.getNama()).removeValue();
                    }
                });
                myViewHolder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        Intent intent = new Intent(list_data_dokter.this,upload_data_dokter.class);
                        intent.putExtra("IDNYA",data_dokter.getNama());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public holder_dokter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewlist_dokter,parent,false);
                return new holder_dokter(v);
            }
        };
        adapter.startListening();
        list.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(list_data_dokter.this, MenuAdmin.class));
    }
}
