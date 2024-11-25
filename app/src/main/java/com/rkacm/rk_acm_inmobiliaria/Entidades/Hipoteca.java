/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.Entidades;

/*
    Clase Hipoteca donde creamos atributos, constructores, getters y setters.
    Ademas de crear un metodo para clacular la hipoteca.
*/
public class Hipoteca {
    private Integer precio, impuestoCompra, precioImpuestoCompra;
    private double ahorroAportado, tipo;
    private int anyos;

    public Hipoteca() {
    }

    public int getAnyos() {
        return anyos;
    }

    public void setAnyos(int anyos) {
        this.anyos = anyos;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getImpuestoCompra() {
        return impuestoCompra;
    }

    public void setImpuestoCompra(Integer impuestoCompra) {
        this.impuestoCompra = impuestoCompra;
    }

    public double getPrecioImpuestoCompra() {
        return precioImpuestoCompra;
    }

    public void setPrecioImpuestoCompra(Integer precioImpuestoCompra) {
        this.precioImpuestoCompra = precioImpuestoCompra;
    }

    public double getAhorroAportado() {
        return ahorroAportado;
    }

    public void setAhorroAportado(double ahorroAportado) {
        this.ahorroAportado = ahorroAportado;
    }

    public double getTipo() {
        return tipo;
    }

    public void setTipo(double tipo) {
        this.tipo = tipo;
    }
}
