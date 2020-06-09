package com.dreamsense.MascotasFavsemana5.presentador;

import android.content.Context;

import com.dreamsense.MascotasFavsemana5.db.ConstructorMascotas;
import com.dreamsense.MascotasFavsemana5.fragments.IRecyclerViewFragmentView;
import com.dreamsense.MascotasFavsemana5.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Gabriel on 05/08/2016.
 */
public class RecyclerViewFragmentPresenter implements IRecyclcerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas =  new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
