package com.suwardi.icheck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PasienAdapter2 extends RecyclerView.Adapter<PasienAdapter2.SearchViewHolder> {

    Context context;
    ArrayList<String> idL;
    ArrayList<String> nipL;
    ArrayList<String> urlL;
    ArrayList<String> namaL ;
    ArrayList<String> umurL ;
    ArrayList<String> nopeL ;
    ArrayList<String> nope2L ;
    ArrayList<String> tanggalL ;
    ArrayList<String> alamatL ;
    ArrayList<String> jkL ;
    ArrayList<String> bbL ;
    ArrayList<String> rpL ;


    public PasienAdapter2() { }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        View v;
        ImageView iv_photo;
        TextView tv_nama,tv_umur,tv_nope,tv_nope2,tv_tanggal,tv_alamat,id,nip,tv_jk,tv_bb,tv_rp;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_photo = (ImageView) itemView.findViewById(R.id.photo);
            id = (TextView) itemView.findViewById(R.id.nik);
            nip = (TextView) itemView.findViewById(R.id.nip);
            tv_nama = (TextView) itemView.findViewById(R.id.nama);
            tv_umur = (TextView) itemView.findViewById(R.id.umur);
            tv_nope = (TextView) itemView.findViewById(R.id.nope);
            tv_nope2 = (TextView) itemView.findViewById(R.id.nope_wali);
            tv_tanggal = (TextView) itemView.findViewById(R.id.tgl_lahir);
            tv_alamat = (TextView) itemView.findViewById(R.id.alamat);
            tv_jk = itemView.findViewById(R.id.jk);
            tv_bb = itemView.findViewById(R.id.bb);
            tv_rp = itemView.findViewById(R.id.rp);

            v = itemView;
        }
    }

    public PasienAdapter2(Context context, ArrayList<String> idL , ArrayList<String> urlL , ArrayList<String> namaL, ArrayList<String> umurL, ArrayList<String> nopeL, ArrayList<String> nope2L, ArrayList<String> tanggalL, ArrayList<String> alamatL, ArrayList<String> nipL, ArrayList<String> jkL, ArrayList<String> bbL, ArrayList<String> rpL) {
        this.context = context;
        this.idL = idL;
        this.urlL = urlL;
        this.namaL = namaL;
        this.umurL = umurL;
        this.nopeL = nopeL;
        this.nope2L = nope2L;
        this.tanggalL = tanggalL;
        this.alamatL = alamatL;
        this.nipL = nipL;
        this.jkL = jkL;
        this.bbL = bbL;
        this.rpL = rpL;
    }


    @NonNull
    @Override
    public PasienAdapter2.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewlist_pasien, parent, false);
        return new PasienAdapter2.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PasienAdapter2.SearchViewHolder holder, final int position) {
        holder.nip.setText(nipL.get(position));
        holder.id.setText(idL.get(position));
        Picasso.get().load(urlL.get(position)).into(holder.iv_photo);
        holder.tv_nama.setText(namaL.get(position));
        holder.tv_umur.setText(umurL.get(position));
        holder.tv_nope.setText(nopeL.get(position));
        holder.tv_nope2.setText(nope2L.get(position));
        holder.tv_tanggal.setText(tanggalL.get(position));
        holder.tv_alamat.setText(alamatL.get(position));
        holder.tv_jk.setText(jkL.get(position));
        holder.tv_bb.setText(bbL.get(position));
        holder.tv_rp.setText(rpL.get(position));

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, list_jadwal2.class);
                intent.putExtra("nik",idL.get(position));
                intent.putExtra("ID",nipL.get(position));
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                ((Activity)context).finish();
            }

        });

    }

    @Override
    public int getItemCount() {
        return idL.size();
    }
}
