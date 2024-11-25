/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.Entidades;

/*
    Clase Vivienda donde creamos atributos, constructores, getters y setters.
 */
public class Vivienda {
    private String id;
    private String nombre, referencia;
    private Integer precio;

    public Vivienda() {

    }

    public Vivienda(String id, String nombre, Integer precio, String toString, String referencia) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.referencia = referencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
}
