package com.suwardi.icheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class opsi_data_jadwal extends AppCompatActivity {

    private Button obat,checkup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_data_jadwal);

        obat = findViewById(R.id.obat);
        checkup = findViewById(R.id.checkup);

        obat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(opsi_data_jadwal.this, tu_list_jadwal_obat.class));
            }
        });
        checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(opsi_data_jadwal.this, tu_list_jadwal_checkup.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(opsi_data_jadwal.this, MenuAdmin.class);
        startActivity(i);
    }
}
