package com.dreamsense.MascotasFavsemana5.fragments;

import com.dreamsense.MascotasFavsemana5.adaptador.MascotaAdaptador;
import com.dreamsense.MascotasFavsemana5.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Gabriel on 05/08/2016.
 */
public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

}
