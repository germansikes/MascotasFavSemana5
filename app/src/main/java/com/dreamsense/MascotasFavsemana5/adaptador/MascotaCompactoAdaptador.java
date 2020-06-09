package com.dreamsense.MascotasFavsemana5.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamsense.MascotasFavsemana5.R;
import com.dreamsense.MascotasFavsemana5.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Gabriel on 14/07/2016.
 */
public class MascotaCompactoAdaptador extends RecyclerView.Adapter<MascotaCompactoAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaCompactoAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_compact, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder holder, int position) {

        final Mascota mascota = mascotas.get(position);
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvLikesDog.setText(mascota.getLikes()+"");
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private ImageView imgLikes;
        private TextView tvLikesDog;


        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgMascota);
            imgLikes = (ImageView) itemView.findViewById(R.id.imgLikes);
            tvLikesDog = (TextView) itemView.findViewById(R.id.txtLikesDog);
        }
    }
}
