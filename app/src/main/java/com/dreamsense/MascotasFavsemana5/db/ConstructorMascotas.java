package com.dreamsense.MascotasFavsemana5.db;

import android.content.ContentValues;
import android.content.Context;

import com.dreamsense.MascotasFavsemana5.R;
import com.dreamsense.MascotasFavsemana5.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Gabriel on 05/08/2016.
 */
public class ConstructorMascotas {
    private static final int LIKE = 1;
    private Context context;
    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){
        BaseDatos db = new BaseDatos(context);
        if(db.obtenerMascotas() == null){
            insertarMascotas(db);
        }else{
            db.onUpgrade(db.getReadableDatabase(), 0, 1);
            insertarMascotas(db);
        }
        return db.obtenerMascotas();
    }

    public void insertarMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Yayo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog1);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Pancho");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog2);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Roger");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog3);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Ari");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog4);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Luna");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog5);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Taco");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog6);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Burguer");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog7);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Mojo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog8);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Merlín");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog9);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Fritanga");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog10);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE, "Boya");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_FOTO, R.drawable.dog11);

        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_LIKED_ID_REF, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTS_LIKED_NLIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesContacto(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

}
