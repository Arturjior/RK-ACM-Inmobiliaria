/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.compra;

import java.util.List;

/*
    Clase Compra donde creamos atributos, contructores, getters y setters.
 */
public class Compra {
    private List<String> tipovivienda;
    private List<String> ordenacion;
    private List<String> localidad;

    public Compra() {

    }

    public List<String> getTipovivienda() {
        return tipovivienda;
    }

    public void setTipovivienda(List<String> tipovivienda) {
        this.tipovivienda = tipovivienda;
    }

    public List<String> getOrdenacion() {
        return ordenacion;
    }

    public void setOrdenacion(List<String> ordenacion) {
        this.ordenacion = ordenacion;
    }

    public List<String> getLocalidad() {
        return localidad;
    }

    public void setLocalidad(List<String> localidad) {
        this.localidad = localidad;
    }
}
