/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.contacto;

/*
    Clase Contacto donde creamos los atributos, los constructores de la clase y los getters y setters
 */
public class Contacto {
    private String telefono1, telefono2, email, tituloFormulario, tituloContacto, textoContacto, oficina, checkBox;
    private int iconoDireccion, iconoTelefono, iconoEmail;

    public Contacto() {
    }

    public Contacto(String telefono1, String telefono2, String email, String tituloFormulario, String tituloContacto, String textoContacto, String oficina, int iconoDireccion,
                    int iconoTelefono, int iconoEmail, String checkBox) {
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email = email;
        this.tituloFormulario = tituloFormulario;
        this.tituloContacto = tituloContacto;
        this.textoContacto = textoContacto;
        this.oficina = oficina;
        this.iconoDireccion = iconoDireccion;
        this.iconoTelefono = iconoTelefono;
        this.iconoEmail = iconoEmail;
        this.checkBox = checkBox;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTituloFormulario() {
        return tituloFormulario;
    }

    public void setTituloFormulario(String tituloFormulario) {
        this.tituloFormulario = tituloFormulario;
    }

    public String getTituloContacto() {
        return tituloContacto;
    }

    public void setTituloContacto(String tituloContacto) {
        this.tituloContacto = tituloContacto;
    }

    public String getTextoContacto() {
        return textoContacto;
    }

    public void setTextoContacto(String textoContacto) {
        this.textoContacto = textoContacto;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public int getIconoDireccion() {
        return iconoDireccion;
    }

    public void setIconoDireccion(int iconoDireccion) {
        this.iconoDireccion = iconoDireccion;
    }

    public int getIconoTelefono() {
        return iconoTelefono;
    }

    public void setIconoTelefono(int iconoTelefono) {
        this.iconoTelefono = iconoTelefono;
    }

    public int getIconoEmail() {
        return iconoEmail;
    }

    public void setIconoEmail(int iconoEmail) {
        this.iconoEmail = iconoEmail;
    }

    public String getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(String checkBox) {
        this.checkBox = checkBox;
    }
}
