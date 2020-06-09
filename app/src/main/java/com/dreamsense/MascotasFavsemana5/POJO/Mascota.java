package com.dreamsense.MascotasFavsemana5.pojo;

/**
 * Created by Gabriel on 06/07/2016.
 */
public class Mascota {
    private int id;
    private int foto;
    private String nombre;
    private int likes;

    public Mascota(int foto, String nombre, int likes) {
        this.foto = foto;
        this.nombre = nombre;
        this.likes = likes;
    }

    public Mascota(int foto, int likes) {
        this.foto = foto;
        this.likes = likes;
    }

    public Mascota() {

    }

    public int getFoto() {

        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
