package com.dreamsense.MascotasFavsemana5.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dreamsense.MascotasFavsemana5.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Gabriel on 08/08/2016.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTS + " (" +
                ConstantesBaseDatos.TABLE_MASCOTS_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTS_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTS_FOTO + " INTEGER"
                + ") ";
        String queryTablaLikesMascota = "CREATE TABLE "+ ConstantesBaseDatos.TABLE_MASCOTS_LIKED + " ("
                + ConstantesBaseDatos.TABLE_MASCOTS_LIKED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesBaseDatos.TABLE_MASCOTS_LIKED_ID_REF + " INTEGER, "
                + ConstantesBaseDatos.TABLE_MASCOTS_LIKED_NLIKES + " INTEGER, "
                +" FOREIGN KEY (" + ConstantesBaseDatos.TABLE_MASCOTS_LIKED_ID_REF + ") "
                + "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTS + "(" + ConstantesBaseDatos.TABLE_MASCOTS_ID + ")"
                + ")";
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Se elimina la tabla si ya existe
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTS_LIKED);
        onCreate(db);

    }

    public ArrayList<Mascota> obtenerMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTS;
        //Se crea una base de datos para escribir sobre ella.
        SQLiteDatabase db = this.getWritableDatabase();
        //El objeto cursor permitirá acceder y recorrer los registros de la DB
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Mascota mascotaactual = new Mascota();
            mascotaactual.setId(registros.getInt(0));
            mascotaactual.setNombre(registros.getString(1));
            mascotaactual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_MASCOTS_LIKED_NLIKES + ") as likes "
                    + "FROM " + ConstantesBaseDatos.TABLE_MASCOTS_LIKED + " WHERE " + ConstantesBaseDatos.TABLE_MASCOTS_LIKED_ID_REF
                    + " = " + mascotaactual.getId();
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                mascotaactual.setLikes(registrosLikes.getInt(0));
            }else{
                mascotaactual.setLikes(0);
            }
            mascotas.add(mascotaactual);
        }

        db.close();

        return  mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTS, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTS_LIKED, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;

        //Se selecciona y suma la cantidad de likes en la tabla mascotas y se compara con el elemento
        //likes del objeto macota.
        String query = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_MASCOTS_LIKED_NLIKES + ")" +
                " FROM "+ ConstantesBaseDatos.TABLE_MASCOTS_LIKED +
                " WHERE " + ConstantesBaseDatos.TABLE_MASCOTS_LIKED_ID_REF + " = " +
                mascota.getId();


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);


        if(registros.moveToNext()){
           likes = registros.getInt(0);
        }

        db.close();
        return likes;
    }

}
