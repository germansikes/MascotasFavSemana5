package com.dreamsense.MascotasFavsemana5.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreamsense.MascotasFavsemana5.R;
import com.dreamsense.MascotasFavsemana5.adaptador.MascotaAdaptador;
import com.dreamsense.MascotasFavsemana5.adaptador.MascotaCompactoAdaptador;
import com.dreamsense.MascotasFavsemana5.pojo.Mascota;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPerfil extends Fragment {

    private CircularImageView circularImageView;
    private RecyclerView lista;
    private ArrayList<Mascota> mascotas;

    public FragmentPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_perfil, container, false);
        circularImageView = (CircularImageView) v.findViewById(R.id.circularImage);
        lista = (RecyclerView) v.findViewById(R.id.gridImgs);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        lista.setLayoutManager(glm);
        inicializarListaMascotaPropia();
        inicializarAdaptador();
        respaldo();
        return v;
    }

    public void inicializarAdaptador() {
        MascotaCompactoAdaptador adaptador = new MascotaCompactoAdaptador(mascotas, getActivity());
        lista.setAdapter(adaptador);
    }

    public void inicializarListaMascotaPropia() {
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota(R.drawable.pug, 5));
        mascotas.add(new Mascota(R.drawable.pug1, 15));
        mascotas.add(new Mascota(R.drawable.pug2, 3));
        mascotas.add(new Mascota(R.drawable.pug3, 9));
        mascotas.add(new Mascota(R.drawable.pug4, 15));
        mascotas.add(new Mascota(R.drawable.pug5, 20));
        mascotas.add(new Mascota(R.drawable.pug6, 32));
        mascotas.add(new Mascota(R.drawable.pug7, 51));
        mascotas.add(new Mascota(R.drawable.pug8, 15));
        mascotas.add(new Mascota(R.drawable.pug9, 42));
        mascotas.add(new Mascota(R.drawable.pug10, 7));
        mascotas.add(new Mascota(R.drawable.pug11, 9));
        mascotas.add(new Mascota(R.drawable.pug12, 13));
        mascotas.add(new Mascota(R.drawable.pug13, 50));
    }

    public void respaldo() {
        if (!MascotaAdaptador.mascotasFavs.isEmpty()) {
            for (int x = 0, y = 0; x < mascotas.size() - 1 && y < MascotaAdaptador.mascotasFavs.size() - 1; x++) {
                if (mascotas.get(x).getNombre() == MascotaAdaptador.mascotasFavs.get(y).getNombre()) {
                    mascotas.get(x).setLikes(MascotaAdaptador.mascotasFavs.get(x).getLikes());
                    y++;
                }
            }
        }
    }

}
