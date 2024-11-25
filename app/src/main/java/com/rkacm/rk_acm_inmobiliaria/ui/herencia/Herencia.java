/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.herencia;

/*
    Clase Herencia donde creamos atributos, constructores, getters y setters.
 */
public class Herencia {
    private String titulo, informacion, ahorra, noAdelantes, olvida, tranquilidad, guiaHerencia;
    private int imgAhorra, imgNoAdelantes, imgOlvida, imgTranquilidad;

    public Herencia() {
    }

    public Herencia(String titulo, String informacion, int imgAhorra, int imgNoAdelantes, int imgOlvida, int imgTranquilidad,
                    String ahorra, String noAdelantes, String olvida, String tranquilidad, String guiaHerencia) {
        this.titulo = titulo;
        this.informacion = informacion;
        this.imgAhorra = imgAhorra;
        this.imgNoAdelantes = imgNoAdelantes;
        this.imgOlvida = imgOlvida;
        this.imgTranquilidad = imgTranquilidad;
        this.ahorra = ahorra;
        this.noAdelantes = noAdelantes;
        this.olvida = olvida;
        this.tranquilidad = tranquilidad;
        this.guiaHerencia = guiaHerencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public int getImgAhorra() {
        return imgAhorra;
    }

    public void setImgAhorra(int imgAhorra) {
        this.imgAhorra = imgAhorra;
    }

    public int getImgNoAdelantes() {
        return imgNoAdelantes;
    }

    public void setImgNoAdelantes(int imgNoAdelantes) {
        this.imgNoAdelantes = imgNoAdelantes;
    }

    public int getImgOlvida() {
        return imgOlvida;
    }

    public void setImgOlvida(int imgOlvida) {
        this.imgOlvida = imgOlvida;
    }

    public int getImgTranquilidad() {
        return imgTranquilidad;
    }

    public void setImgTranquilidad(int imgTranquilidad) {
        this.imgTranquilidad = imgTranquilidad;
    }

    public String getAhorra() {
        return ahorra;
    }

    public void setAhorra(String ahorra) {
        this.ahorra = ahorra;
    }

    public String getNoAdelantes() {
        return noAdelantes;
    }

    public void setNoAdelantes(String noAdelantes) {
        this.noAdelantes = noAdelantes;
    }

    public String getOlvida() {
        return olvida;
    }

    public void setOlvida(String olvida) {
        this.olvida = olvida;
    }

    public String getTranquilidad() {
        return tranquilidad;
    }

    public void setTranquilidad(String tranquilidad) {
        this.tranquilidad = tranquilidad;
    }

    public String getGuiaHerencia() {
        return guiaHerencia;
    }

    public void setGuiaHerencia(String guiaHerencia) {
        this.guiaHerencia = guiaHerencia;
    }
}
