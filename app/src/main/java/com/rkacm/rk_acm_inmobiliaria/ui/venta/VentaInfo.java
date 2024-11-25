/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.venta;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

import com.rkacm.rk_acm_inmobiliaria.R;

/*
    Clase VentaInfo donde creamos una nueva venta.
    Creamos un static donde seteamos la informacion que vamos a mostrar en la vista de venta.
    Y por ultimo retornamos venta para obtener los datos anteriormente metidos.
 */
public class VentaInfo {
    private static Venta venta = new Venta();

    static {
        venta.setPrincipal1("VENDE TU CASA EN JAÉN");
        venta.setPrincipal2("Multiplicamos el valor");
        venta.setPrincipal3("Descargar Guía");

        String vende = "¡Vende tu casa en menos de 10 semanas!";
        String txtVenta = vende + " Descarga nuestra guía para descubrir cómo lo hacemos y cómo " +
                "aplicamos las siguientes herramientas para aumentar el valor de tu casa.";
        SpannableStringBuilder strVenta = new SpannableStringBuilder(txtVenta);
        strVenta.setSpan(new StyleSpan(Typeface.BOLD), 0, vende.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        venta.setPrincipal4(strVenta);

        venta.setHomeStaging("Home Staging");
        venta.setReportaje("Reportaje Profesional");
        venta.setEscaneado("Escaneado 3D");
        venta.setReforma("Proyecto de Reforma");
        venta.setCompradores("Base de Datos de Compradores");
        venta.setValoracion("Valoracion Propiedad");
        venta.setCarteles("Tour Virtual");
        venta.setEscaparates("Escaparates y Carteles");
        venta.setWeb("Pagina Web y RRSS");
        venta.setPortales("Portales Inmobiliarios");
        venta.setColaboracion("Colaboracion Inmobiliaria");
        venta.setFlyers("Marketing 4.0 o 360º");
        venta.setImagenHomeStaging(R.drawable.plantas);
        venta.setImagenReportaje(R.drawable.camara);
        venta.setImagenEscaneado(R.drawable.plano);
        venta.setImagenReforma(R.drawable.reforma);
        venta.setImagenCompradores(R.drawable.acuerdo);
        venta.setImagenValoracion(R.drawable.valoracion);
        venta.setImagenCarteles(R.drawable.tourvirtual);
        venta.setImagenEscaparates(R.drawable.escaparate);
        venta.setImagenWeb(R.drawable.web);
        venta.setImagenPortales(R.drawable.portales);
        venta.setImagenColaboracion(R.drawable.colaboracion);
        venta.setImagenFlyers(R.drawable.marketing360);
    }

    public static Venta getVenta() {
        return venta;
    }
}
