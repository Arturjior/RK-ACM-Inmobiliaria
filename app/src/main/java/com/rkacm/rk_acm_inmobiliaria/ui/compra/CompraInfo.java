/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.compra;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
    Clase CompraInfo donde creamos una nueva compra.
    Creamos un static donde seteamos la informacion que vamos a mostrar en la vista de compra.
    Y por ultimo retornamos compra para obtener los datos anteriormente metidos.
 */
public class CompraInfo extends ViewModel {
    private static Compra compra = new Compra();

    static {
        List<String> tipos = new ArrayList<>();
        tipos.add("Tipo");
        tipos.addAll(Arrays.asList("Chalet", "Piso", "Campo", "Adosado", "Parking"));
        compra.setTipovivienda(tipos);

        List<String> ordenacion = new ArrayList<>();
        ordenacion.add("Ordenar");
        ordenacion.addAll(Arrays.asList("Más reciente", "Más antiguo", "Precio venta más alto", "Precio venta más bajo"));
        compra.setOrdenacion(ordenacion);

        List<String> localidadesJaen = new ArrayList<>();
        localidadesJaen.add("Localidad");
        localidadesJaen.addAll(Arrays.asList("Jaén", "Puente Tablas", "Jabalcuz", "Ant. Carretera Granada", "Ciudad Jardín",
                "Valdeastillas", "Altos Puente Nuevo", "Todos Puentes", "Manseguilla", "Bergel", "Cerro Molina", "Tentesón",
                "Vadillos"));
        Collections.sort(localidadesJaen.subList(1, localidadesJaen.size()));
        compra.setLocalidad(localidadesJaen);
    }

    public static Compra getCompra() {
        return compra;
    }
}