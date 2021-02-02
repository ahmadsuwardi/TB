package com.suwardi.icheck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.SearchViewHolder> {

    Context context;
    ArrayList<String> idL;
    ArrayList<String> nipL;
    ArrayList<String> photoL;
    ArrayList<String> namaL;
    ArrayList<String> umurL;
    ArrayList<String> nopeL;
    ArrayList<String> nope_waliL;
    ArrayList<String> tgl_lahirL;
    ArrayList<String> alamatL;

    ArrayList<String> obat;
    ArrayList<String> checkup;
    ArrayList<String> send;

    public AdapterLaporan() {
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        TextView tv_nama,tv_umur,tv_nope,tv_nope_wali,tv_tgl_lahir,tv_alamat,tv_nik,tv_nip;
        Button bt_send,bt_obat,bt_checkup;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo);
            tv_nik = (TextView) itemView.findViewById(R.id.nik);
            tv_nip = (TextView) itemView.findViewById(R.id.nip);
            tv_nama = (TextView) itemView.findViewById(R.id.nama);
            tv_umur = (TextView) itemView.findViewById(R.id.umur);
            tv_nope = (TextView) itemView.findViewById(R.id.nope);
            tv_nope_wali = (TextView) itemView.findViewById(R.id.nope_wali);
            tv_tgl_lahir = (TextView) itemView.findViewById(R.id.tgl_lahir);
            tv_alamat = (TextView) itemView.findViewById(R.id.alamat);

            bt_checkup = (Button) itemView.findViewById(R.id.checkup);
            bt_obat = (Button) itemView.findViewById(R.id.obat);
            bt_send = (Button) itemView.findViewById(R.id.send);
        }
    }

    public AdapterLaporan(Context context, ArrayList<String> photoL, ArrayList<String> nipL, ArrayList<String> idL, ArrayList<String> namaL, ArrayList<String> umurL, ArrayList<String> nopeL, ArrayList<String> nope_waliL, ArrayList<String> tgl_lahirL, ArrayList<String> alamatL, ArrayList<String> obat, ArrayList<String> checkup, ArrayList<String> send) {
        this.context = context;
        this.photoL = photoL;
        this.nipL = nipL;
        this.idL = idL;
        this.namaL = namaL;
        this.umurL = umurL;
        this.nopeL = nopeL;
        this.nope_waliL = nope_waliL;
        this.tgl_lahirL = tgl_lahirL;
        this.alamatL = alamatL;
        this.obat = obat;
        this.checkup = checkup;
        this.send = send;
    }

    @NonNull
    @Override
    public AdapterLaporan.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewlist_laporan, parent, false);
        return new AdapterLaporan.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLaporan.SearchViewHolder holder, final int position) {
        Picasso.get().load(photoL.get(position)).into(holder.photo);
        holder.tv_nip.setText(nipL.get(position));
        holder.tv_nik.setText(idL.get(position));
        holder.tv_nama.setText(namaL.get(position));
        holder.tv_umur.setText(umurL.get(position));
        holder.tv_nope.setText(nopeL.get(position));
        holder.tv_nope_wali.setText(nope_waliL.get(position));
        holder.tv_tgl_lahir.setText(tgl_lahirL.get(position));
        holder.tv_alamat.setText(alamatL.get(position));

        holder.bt_obat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, laporan_next.class);
                intent.putExtra("nik",idL.get(position));
                intent.putExtra("nip",nipL.get(position));
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

        holder.bt_checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, laporan_next2.class);
                intent.putExtra("nip",nipL.get(position));
                intent.putExtra("nik",idL.get(position));
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

        holder.bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get the default instance of the SmsManager
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(nopeL.get(position),
                            null,
                            "Hallo "+namaL.get(position)+", Tolong rajin minum obat dan checkup ya!",
                            null,
                            null);
                    Toast.makeText(context, "Pengiriman Berhasil",
                            Toast.LENGTH_LONG).show();

                } catch (Exception ex) {
                    Toast.makeText(context,"Pengiriman SMS Gagal...",
                            Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return idL.size();
    }
}
