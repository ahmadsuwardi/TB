package com.suwardi.icheck;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class holder_dokter extends RecyclerView.ViewHolder {
    ImageView iv_photo;
    TextView tv_nip,tv_nama,tv_spesialis,tv_nope,tv_alamat;
    ImageButton del,edit;
    View v;

    public holder_dokter(@NonNull View itemView) {
        super(itemView);
        iv_photo = itemView.findViewById(R.id.photo);
        tv_nip = itemView.findViewById(R.id.nip);
        tv_nama = itemView.findViewById(R.id.nama);
        tv_spesialis = itemView.findViewById(R.id.spesialis);
        tv_nope = itemView.findViewById(R.id.nope);
        tv_alamat = itemView.findViewById(R.id.alamat);
        del = itemView.findViewById(R.id.del_dokter);
        edit = itemView.findViewById(R.id.edit_dokter);
        v = itemView;
    }
}
