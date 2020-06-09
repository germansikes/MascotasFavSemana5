package com.dreamsense.MascotasFavsemana5.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreamsense.MascotasFavsemana5.R;
import com.dreamsense.MascotasFavsemana5.adaptador.MascotaAdaptador;
import com.dreamsense.MascotasFavsemana5.pojo.Mascota;
import com.dreamsense.MascotasFavsemana5.presentador.IRecyclcerViewFragmentPresenter;
import com.dreamsense.MascotasFavsemana5.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecycler extends Fragment implements IRecyclerViewFragmentView{

    private RecyclerView listaMascotas;
    ArrayList<Mascota> mascotas;
    private IRecyclcerViewFragmentPresenter presenter;

    public FragmentRecycler() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_recycler, container, false);
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
