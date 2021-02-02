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

public class CheckupAdapter extends RecyclerView.Adapter<CheckupAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> idL;
    ArrayList<String> waktuL;


    public CheckupAdapter() { }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        TextView tv_jadwal,id;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            tv_jadwal = (TextView) itemView.findViewById(R.id.waktu);
        }
    }


    public CheckupAdapter(Context context, ArrayList<String> idL, ArrayList<String> waktuL) {
        this.context = context;
        this.idL = idL;
        this.waktuL = waktuL;
    }

    @NonNull
    @Override
    public CheckupAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewlist_checkup, parent, false);
        return new CheckupAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CheckupAdapter.SearchViewHolder holder, final int position) {

        holder.id.setText(idL.get(position));
        holder.tv_jadwal.setText(waktuL.get(position));

    }

    @Override
    public int getItemCount() {
        return idL.size();
    }
}
