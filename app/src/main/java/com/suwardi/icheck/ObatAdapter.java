package com.suwardi.icheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> idL;
    ArrayList<String> waktuL;


    public ObatAdapter() { }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        TextView tv_jadwal,id;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            tv_jadwal = (TextView) itemView.findViewById(R.id.waktu);
        }
    }


    public ObatAdapter(Context context, ArrayList<String> idL, ArrayList<String> waktuL) {
        this.context = context;
        this.idL = idL;
        this.waktuL = waktuL;
    }

    @NonNull
    @Override
    public ObatAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewlist_checkup, parent, false);
        return new ObatAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ObatAdapter.SearchViewHolder holder, final int position) {

        holder.id.setText(idL.get(position));
        holder.tv_jadwal.setText(waktuL.get(position));

    }

    @Override
    public int getItemCount() {
        return idL.size();
    }
}
