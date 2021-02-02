package com.suwardi.icheck;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class holder_laporan extends RecyclerView.ViewHolder {
    ImageView iv_photo;
    TextView tv_nama,tv_umur,tv_nope,tv_nope_wali,tv_tgl,tv_alamat,tv_total,tv_jadwal,tv_checkup;

    public holder_laporan(@NonNull View itemView) {
        super(itemView);
        iv_photo = itemView.findViewById(R.id.photo);
        tv_nama = itemView.findViewById(R.id.nama);
        tv_umur = itemView.findViewById(R.id.umur);
        tv_nope = itemView.findViewById(R.id.nope);
        tv_nope_wali = itemView.findViewById(R.id.nope_wali);
        tv_tgl = itemView.findViewById(R.id.tgl_lahir);
        tv_alamat = itemView.findViewById(R.id.alamat);
        tv_total = itemView.findViewById(R.id.total);
        tv_jadwal = itemView.findViewById(R.id.jadwal);
        tv_checkup = itemView.findViewById(R.id.checkup);
    }


}
