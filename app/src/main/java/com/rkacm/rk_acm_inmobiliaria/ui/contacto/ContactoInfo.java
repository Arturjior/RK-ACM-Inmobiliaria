/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.contacto;

import com.rkacm.rk_acm_inmobiliaria.R;

/*
    Clase ContactoInfo donde creamos un nuevo contacto.
    Creamos un static donde seteamos la informacion que vamos a mostrar en la vista de contacto.
    Y por ultimo retornamos contacto para obtener los datos anteriormente metidos.
 */
public class ContactoInfo {
    private static Contacto contacto = new Contacto();

    static {
        contacto.setTelefono1("953 22 30 83");
        contacto.setTelefono2("669 76 72 65");
        contacto.setEmail("info@inmobiliariaacm.com");
        contacto.setTituloContacto("CONTACTO");
        contacto.setTextoContacto("Puedes llamarnos, enviarnos un mensaje o pasarte por nuestra oficina en JAÉN y nos tomamos un café.\n" +
                "¡Te esperamos!");
        contacto.setOficina("C/ Doctor Eduardo García-Triviño Nº 8, Local ACM 23009 (Jaén)");
        contacto.setIconoDireccion(R.drawable.ubicacion);
        contacto.setIconoTelefono(R.drawable.llamada);
        contacto.setIconoEmail(R.drawable.email);
        contacto.setTituloFormulario("Formulario de Contacto");
        contacto.setCheckBox("Para enviar el formulario debes aceptar nuestra Política de Privacidad" +
                " | Responsable de los datos: RK INMOBILIARIA ACM | Finalidad: Contacto con usuarios" +
                " | Legitimación: Tu consentimiento expreso| Derechos: Tienes derecho al acceso, rectificación, " +
                "supresión, limitación, portabilidad y olvido de sus datos. *");
    }

    public static Contacto getContacto() {
        return contacto;
    }
}
