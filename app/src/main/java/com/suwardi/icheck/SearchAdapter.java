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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> waktuL ;
    ArrayList<String> nipL ;
    ArrayList<String> idL ;
    ArrayList<String> uidL ;

    public SearchAdapter(ArrayList<String> waktuL, ArrayList<String> nipL, ArrayList<String> idL, ArrayList<String> uidL) {
    }


    class SearchViewHolder extends RecyclerView.ViewHolder{
        View v;
        TextView waktu, nip,id,nik;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            waktu = (TextView) itemView.findViewById(R.id.waktu);
            nip = (TextView) itemView.findViewById(R.id.nip);
            nik = (TextView) itemView.findViewById(R.id.nik);
            id = (TextView) itemView.findViewById(R.id.id);
            v = itemView;
        }
    }

    public SearchAdapter(Context context, ArrayList<String> waktuL, ArrayList<String> nipL, ArrayList<String> idL, ArrayList<String> uidL) {
        this.context = context;
        this.waktuL = waktuL;
        this.nipL = nipL;
        this.idL = idL;
        this.uidL = uidL;

    }





    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewlist_checkup, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchViewHolder holder, final int position) {

        holder.waktu.setText(waktuL.get(position));
        holder.nip.setText(nipL.get(position));
        holder.nik.setText(uidL.get(position));
        holder.id.setText(idL.get(position));

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, jadwalnya.class);
                intent.putExtra("waktu",waktuL.get(position));
                intent.putExtra("ID",nipL.get(position));
                intent.putExtra("ID2",uidL.get(position));
                intent.putExtra("ID3",idL.get(position));
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                ((Activity)context).finish();
            }

        });

    }

    @Override
    public int getItemCount() {
        return nipL.size();
    }
}
