/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.herencia;

import com.rkacm.rk_acm_inmobiliaria.R;

/*
    Clase HerenciaInfo donde creamos una nueva herencia.
    Creamos un static donde seteamos la informacion que vamos a mostrar en la vista de herencia.
    Y por ultimo retornamos herencia para obtener los datos anteriormente metidos.
 */
public class HerenciaInfo {
    private static Herencia herencia = new Herencia();

    static {
        herencia.setTitulo("HERENCIAS");
            herencia.setInformacion("Descargar nuestra guía para descubrir como ahorrar hasta 10.000€ en Impuestos y como " +
                    "podemos ayudarte en la gestión de tu herencia.");
        herencia.setImgAhorra(R.drawable.ahorraenimpuestos);
        herencia.setImgNoAdelantes(R.drawable.noadelantestudinero);
        herencia.setImgOlvida(R.drawable.olvidatramites);
        herencia.setImgTranquilidad(R.drawable.ganaentranquilidad);
        herencia.setAhorra("Ahorra en Impuestos");
        herencia.setNoAdelantes("No Adelantes tu Dinero");
        herencia.setOlvida("Olvida Trámites Complejos");
        herencia.setTranquilidad("Gana en Tranquilidad");
        herencia.setGuiaHerencia("Descargar\n" + "Guía Herencias RK ACM");
    }

    public static Herencia getHerencia() {
        return herencia;
    }
}
