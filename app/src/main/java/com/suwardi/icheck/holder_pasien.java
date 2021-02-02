package com.suwardi.icheck;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class holder_pasien extends RecyclerView.ViewHolder {
    ImageView iv_photo;
    TextView tv_nama,tv_umur,tv_nope,tv_nope_wali,tv_tgl,tv_alamat,tv_nik,tv_nip,tv_bb,tv_rp,tv_jk;

    public holder_pasien(@NonNull View itemView) {
        super(itemView);
        iv_photo = itemView.findViewById(R.id.photo);
        tv_nama = itemView.findViewById(R.id.nama);
        tv_nik = itemView.findViewById(R.id.nik);
        tv_nip = itemView.findViewById(R.id.nip_dokter_);
        tv_bb = itemView.findViewById(R.id.bb);
        tv_rp = itemView.findViewById(R.id.rp);
        tv_jk = itemView.findViewById(R.id.jk);
        tv_umur = itemView.findViewById(R.id.umur);
        tv_nope = itemView.findViewById(R.id.nope);
        tv_nope_wali = itemView.findViewById(R.id.nope_wali);
        tv_tgl = itemView.findViewById(R.id.tgl_lahir);
        tv_alamat = itemView.findViewById(R.id.alamat);
    }
}
