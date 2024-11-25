/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.venta;

/*
    Clase Venta donde creamos atributos, constructores, getters y setters.
 */
public class Venta {
    private String principal1, principal2, principal3, homeStaging, reportaje,
            escaneado, reforma, compradores, valoracion, carteles, escaparates, web, portales,
            colaboracion, flyers;
    private CharSequence principal4;
    private int imagenHomeStaging, imagenReportaje, imagenEscaneado, imagenReforma, imagenCompradores,
            imagenValoracion, imagenCarteles, imagenEscaparates, imagenWeb, imagenPortales, imagenColaboracion,
            imagenFlyers;

    public Venta() {
    }

    public Venta(String principal1, String principal2, String principal3, CharSequence principal4, String homeStaging,
                 String reportaje, String escaneado, String reforma, String compradores, String valoracion, String carteles,
                 String escaparates, String web, String portales, String colaboracion, String flyers, int imagenHomeStaging,
                 int imagenReportaje, int imagenEscaneado, int imagenReforma, int imagenCompradores, int imagenValoracion,
                 int imagenCarteles, int imagenEscaparates, int imagenWeb, int imagenPortales, int imagenColaboracion, int imagenFlyers) {
        this.principal1 = principal1;
        this.principal2 = principal2;
        this.principal3 = principal3;
        this.principal4 = principal4;
        this.homeStaging = homeStaging;
        this.reportaje = reportaje;
        this.escaneado = escaneado;
        this.reforma = reforma;
        this.compradores = compradores;
        this.valoracion = valoracion;
        this.carteles = carteles;
        this.escaparates = escaparates;
        this.web = web;
        this.portales = portales;
        this.colaboracion = colaboracion;
        this.flyers = flyers;
        this.imagenHomeStaging = imagenHomeStaging;
        this.imagenReportaje = imagenReportaje;
        this.imagenEscaneado = imagenEscaneado;
        this.imagenReforma = imagenReforma;
        this.imagenCompradores = imagenCompradores;
        this.imagenValoracion = imagenValoracion;
        this.imagenCarteles = imagenCarteles;
        this.imagenEscaparates = imagenEscaparates;
        this.imagenWeb = imagenWeb;
        this.imagenPortales = imagenPortales;
        this.imagenColaboracion = imagenColaboracion;
        this.imagenFlyers = imagenFlyers;
    }

    public String getPrincipal1() {
        return principal1;
    }

    public void setPrincipal1(String principal1) {
        this.principal1 = principal1;
    }

    public String getPrincipal2() {
        return principal2;
    }

    public void setPrincipal2(String principal2) {
        this.principal2 = principal2;
    }

    public String getPrincipal3() {
        return principal3;
    }

    public void setPrincipal3(String principal3) {
        this.principal3 = principal3;
    }

    public CharSequence getPrincipal4() {
        return principal4;
    }

    public void setPrincipal4(CharSequence principal4) {
        this.principal4 = principal4;
    }

    public String getHomeStaging() {
        return homeStaging;
    }

    public void setHomeStaging(String homeStaging) {
        this.homeStaging = homeStaging;
    }

    public String getReportaje() {
        return reportaje;
    }

    public void setReportaje(String reportaje) {
        this.reportaje = reportaje;
    }

    public String getEscaneado() {
        return escaneado;
    }

    public void setEscaneado(String escaneado) {
        this.escaneado = escaneado;
    }

    public String getReforma() {
        return reforma;
    }

    public void setReforma(String reforma) {
        this.reforma = reforma;
    }

    public String getCompradores() {
        return compradores;
    }

    public void setCompradores(String compradores) {
        this.compradores = compradores;
    }

    public int getImagenHomeStaging() {
        return imagenHomeStaging;
    }

    public void setImagenHomeStaging(int imagenHomeStaging) {
        this.imagenHomeStaging = imagenHomeStaging;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getCarteles() {
        return carteles;
    }

    public void setCarteles(String carteles) {
        this.carteles = carteles;
    }

    public String getEscaparates() {
        return escaparates;
    }

    public void setEscaparates(String escaparates) {
        this.escaparates = escaparates;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getPortales() {
        return portales;
    }

    public void setPortales(String portales) {
        this.portales = portales;
    }

    public String getColaboracion() {
        return colaboracion;
    }

    public void setColaboracion(String colaboracion) {
        this.colaboracion = colaboracion;
    }

    public String getFlyers() {
        return flyers;
    }

    public void setFlyers(String flyers) {
        this.flyers = flyers;
    }

    public int getImagenReportaje() {
        return imagenReportaje;
    }

    public void setImagenReportaje(int imagenReportaje) {
        this.imagenReportaje = imagenReportaje;
    }

    public int getImagenEscaneado() {
        return imagenEscaneado;
    }

    public void setImagenEscaneado(int imagenEscaneado) {
        this.imagenEscaneado = imagenEscaneado;
    }

    public int getImagenReforma() {
        return imagenReforma;
    }

    public void setImagenReforma(int imagenReforma) {
        this.imagenReforma = imagenReforma;
    }

    public int getImagenCompradores() {
        return imagenCompradores;
    }

    public void setImagenCompradores(int imagenCompradores) {
        this.imagenCompradores = imagenCompradores;
    }

    public int getImagenValoracion() {
        return imagenValoracion;
    }

    public void setImagenValoracion(int imagenValoracion) {
        this.imagenValoracion = imagenValoracion;
    }

    public int getImagenCarteles() {
        return imagenCarteles;
    }

    public void setImagenCarteles(int imagenCarteles) {
        this.imagenCarteles = imagenCarteles;
    }

    public int getImagenEscaparates() {
        return imagenEscaparates;
    }

    public void setImagenEscaparates(int imagenEscaparates) {
        this.imagenEscaparates = imagenEscaparates;
    }

    public int getImagenWeb() {
        return imagenWeb;
    }

    public void setImagenWeb(int imagenWeb) {
        this.imagenWeb = imagenWeb;
    }

    public int getImagenPortales() {
        return imagenPortales;
    }

    public void setImagenPortales(int imagenPortales) {
        this.imagenPortales = imagenPortales;
    }

    public int getImagenColaboracion() {
        return imagenColaboracion;
    }

    public void setImagenColaboracion(int imagenColaboracion) {
        this.imagenColaboracion = imagenColaboracion;
    }

    public int getImagenFlyers() {
        return imagenFlyers;
    }

    public void setImagenFlyers(int imagenFlyers) {
        this.imagenFlyers = imagenFlyers;
    }
}
