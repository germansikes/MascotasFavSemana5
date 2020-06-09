package com.dreamsense.MascotasFavsemana5.adaptador;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamsense.MascotasFavsemana5.R;
import com.dreamsense.MascotasFavsemana5.db.ConstructorMascotas;
import com.dreamsense.MascotasFavsemana5.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Gabriel on 06/07/2016.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{


    ArrayList<Mascota> mascotas;
    public static  ArrayList<Mascota> mascotasFavs = new ArrayList<Mascota>();
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cadrdviewmascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, final int position) {
        final Mascota mascota = mascotas.get(position);
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombreDog.setText(mascota.getNombre());
        holder.btnLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aumentar el elemento Likes en uno y agregar cardview a lista de favoritos
                if(mascotasFavs.size() <= 5) {
                    ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                    Snackbar.make(v, "Agregado " + mascota.getNombre() + " a favoritos.", Snackbar.LENGTH_SHORT).show();
                    constructorMascotas.darLikeMascota(mascota);
                    holder.tvLikesDog.setText(constructorMascotas.obtenerLikesContacto(mascota) + "");
                    agregarMascotaFavorita(mascota); //VERIFICAR QUE SE MODIFIQUE EL DATO EN FAVS DE LA DB
                }else{
                    Snackbar.make(v, "Ya no puedes agregar más mascotas a favoritos", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        holder.tvLikesDog.setText(mascota.getLikes()+"");
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public boolean verificaRepetidos(Mascota mascota){
        for (Mascota m: mascotasFavs
             ) {
            if(m.getNombre() == mascota.getNombre())
                return true;
        }
        return false;
    }


    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private ImageView imgLikes;
        private TextView tvNombreDog;
        private TextView tvLikesDog;
        private ImageButton btnLikes;


        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView) itemView.findViewById(R.id.imgMascota);
            imgLikes    = (ImageView) itemView.findViewById(R.id.imgLikes);
            tvNombreDog = (TextView) itemView.findViewById(R.id.txtNombreDog);
            tvLikesDog  = (TextView) itemView.findViewById(R.id.txtLikesDog);
            btnLikes    = (ImageButton) itemView.findViewById(R.id.btnLike);
        }
    }

    public void agregarMascotaFavorita(Mascota mascota){
        ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
        if(constructorMascotas.obtenerLikesContacto(mascota) > 0 && !mascotasFavs.contains(mascota)){
            mascota.setLikes(constructorMascotas.obtenerLikesContacto(mascota));
            mascotasFavs.add(mascota);
        }
    }
}
