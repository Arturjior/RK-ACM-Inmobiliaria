/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.sobreNosotros;

/*
    Clase SobreNosotros donde creamos atributos, constructores, getters y setters.
 */
public class SobreNosotros {
    private int imagenLogo, imagenGrafico1, imagenPorciento, imagenGrafico2, imagenVisitas, imagenPersona, imagenVendemos;
    private String informacion, tituloNosotros;
    private CharSequence txtGrafico1, txtPorciento, txtGrafico2, txtVisitas, txtVendemos2, txtPersona;

    public SobreNosotros() {

    }

    public SobreNosotros(int imagenLogo, int imagenGrafico1, int imagenPorciento, int imagenGrafico2, int imagenVisitas,
                         int imagenPersona, String informacion, String tituloNosotros, CharSequence txtGrafico1, CharSequence txtPorciento,
                         CharSequence txtGrafico2, CharSequence txtVisitas, int imagenVendemos, CharSequence txtVendemos2,
                         CharSequence txtPersona) {
        this.imagenLogo = imagenLogo;
        this.imagenGrafico1 = imagenGrafico1;
        this.imagenPorciento = imagenPorciento;
        this.imagenGrafico2 = imagenGrafico2;
        this.imagenVisitas = imagenVisitas;
        this.imagenPersona = imagenPersona;
        this.informacion = informacion;
        this.tituloNosotros = tituloNosotros;
        this.txtGrafico1 = txtGrafico1;
        this.txtPorciento = txtPorciento;
        this.txtGrafico2 = txtGrafico2;
        this.txtVisitas = txtVisitas;
        this.imagenVendemos = imagenVendemos;
        this.txtVendemos2 = txtVendemos2;
        this.txtPersona = txtPersona;
    }

    public int getImagenLogo() {
        return imagenLogo;
    }

    public void setImagenLogo(int imagenLogo) {
        this.imagenLogo = imagenLogo;
    }

    public int getImagenGrafico1() {
        return imagenGrafico1;
    }

    public void setImagenGrafico1(int imagenGrafico1) {
        this.imagenGrafico1 = imagenGrafico1;
    }

    public int getImagenPorciento() {
        return imagenPorciento;
    }

    public void setImagenPorciento(int imagenPorciento) {
        this.imagenPorciento = imagenPorciento;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getTituloNosotros() {
        return tituloNosotros;
    }

    public void setTituloNosotros(String tituloNosotros) {
        this.tituloNosotros = tituloNosotros;
    }

    public int getImagenGrafico2() {
        return imagenGrafico2;
    }

    public void setImagenGrafico2(int imagenGrafico2) {
        this.imagenGrafico2 = imagenGrafico2;
    }

    public int getImagenVisitas() {
        return imagenVisitas;
    }

    public void setImagenVisitas(int imagenVisitas) {
        this.imagenVisitas = imagenVisitas;
    }

    public int getImagenPersona() {
        return imagenPersona;
    }

    public void setImagenPersona(int imagenPersona) {
        this.imagenPersona = imagenPersona;
    }

    public CharSequence getTxtGrafico1() {
        return txtGrafico1;
    }

    public void setTxtGrafico1(CharSequence txtGrafico1) {
        this.txtGrafico1 = txtGrafico1;
    }

    public CharSequence getTxtPorciento() {
        return txtPorciento;
    }

    public void setTxtPorciento(CharSequence txtPorciento) {
        this.txtPorciento = txtPorciento;
    }

    public CharSequence getTxtGrafico2() {
        return txtGrafico2;
    }

    public void setTxtGrafico2(CharSequence txtGrafico2) {
        this.txtGrafico2 = txtGrafico2;
    }

    public CharSequence getTxtVisitas() {
        return txtVisitas;
    }

    public void setTxtVisitas(CharSequence txtVisitas) {
        this.txtVisitas = txtVisitas;
    }

    public int getImagenVendemos() {
        return imagenVendemos;
    }

    public void setImagenVendemos(int imagenVendemos) {
        this.imagenVendemos = imagenVendemos;
    }

    public CharSequence getTxtVendemos2() {
        return txtVendemos2;
    }

    public void setTxtVendemos2(CharSequence txtVendemos2) {
        this.txtVendemos2 = txtVendemos2;
    }

    public CharSequence getTxtPersona() {
        return txtPersona;
    }

    public void setTxtPersona(CharSequence txtPersona) {
        this.txtPersona = txtPersona;
    }
}
