package com.suwardi.icheck;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuDokter extends AppCompatActivity {

    private Button laporan,laporan_obat,reset,logout,kirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dokter);

        final String ID = getIntent().getStringExtra("ID");

        reset = findViewById(R.id.chng_pw);
        kirim = findViewById(R.id.kirim);
        laporan = findViewById(R.id.laporan);
        laporan_obat = findViewById(R.id.laporan_obat);
        logout = findViewById(R.id.logout);


        laporan_obat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(MenuDokter.this, daftar_pasien_obat.class);
                i.putExtra("ID",ID);
                startActivity(i);


            }
        });

        laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(MenuDokter.this, daftar_pasien_checkup.class);
                i.putExtra("ID",ID);
                startActivity(i);
            }
        });

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent i = new Intent(MenuDokter.this, list_lapor.class);
                i.putExtra("ID",ID);
                startActivity(i);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent i = new Intent(MenuDokter.this, pw_dokter.class);
                i.putExtra("ID",ID);
                startActivity(i);

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MenuDokter.this, opsi.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Keluar Aplikasi?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}