/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.sobreNosotros;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

import com.rkacm.rk_acm_inmobiliaria.R;

/*
    Clase SobreNosotrosInfo donde creamos un nuevo sobreNosotros.
    Creamos un static donde seteamos la informacion que vamos a mostrar en la vista de sobreNosotros.
    Y por ultimo retornamos sobreNosotros para obtener los datos anteriormente metidos.
 */
public class SobreNosotrosInfo {
    private static SobreNosotros sobreNosotros = new SobreNosotros();

    static {
        sobreNosotros.setImagenLogo(R.drawable.logorkacm);
        sobreNosotros.setTituloNosotros("NOSOTROS");
        sobreNosotros.setInformacion("Nuestro negocio es escuchar a las personas. Se trata de hacer las cosas de corazón. De poner por delante los intereses " +
                "de nuestros clientes siempre. De trabajar con pasión con cada caso y " +
                "con cada casa.\n" +
                "\n" +
                "Nuestros clientes nos contratan para que pongamos lo mejor de nosotros mismos. Nuestra experiencia, " +
                "nuestro marketing, nuestra capacidad de negociación, nuestras herramientas, nuestros conocimientos del mercado " +
                "inmobiliario, nuestra actitud, pero sobre todo nuestro corazón.");
        sobreNosotros.setImagenGrafico1(R.drawable.grafico1);

        String diasGrafico = "55 DIAS";
        String txtGrafico1 = diasGrafico + "\nSomos capaces de vender una vivienda en una media de 55 días";
        SpannableStringBuilder strGrafico1 = new SpannableStringBuilder(txtGrafico1);
        strGrafico1.setSpan(new StyleSpan(Typeface.BOLD), 0, diasGrafico.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sobreNosotros.setTxtGrafico1(strGrafico1);

        sobreNosotros.setImagenPorciento(R.drawable.porciento);

        String porciento = "92% RECOMENDAMOS";
        String txtPorciento = porciento + "\nLa gran mayoría de nuestros clientes vienen recomendados";
        SpannableStringBuilder strPorciento = new SpannableStringBuilder(txtPorciento);
        strPorciento.setSpan(new StyleSpan(Typeface.BOLD), 0, porciento.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sobreNosotros.setTxtPorciento(strPorciento);

        sobreNosotros.setImagenGrafico2(R.drawable.grafico2);

        String grafico2 = "4,2%";
        String txtGrafico2 = "Solo un " + grafico2 + " de diferencia entre el precio de captación y precio de cierre";
        SpannableStringBuilder strGrafico2 = new SpannableStringBuilder(txtGrafico2);
        int grafico2Incio = txtGrafico2.indexOf(grafico2);
        int grafico2Fin = grafico2Incio + grafico2.length();
        strGrafico2.setSpan(new StyleSpan(Typeface.BOLD), grafico2Incio, grafico2Fin, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sobreNosotros.setTxtGrafico2(strGrafico2);

        sobreNosotros.setImagenVisitas(R.drawable.visitas);

        String visitas = "VISITAS";
        String txtVisitas = visitas + "\nEn solo 5 visitas vendemos una vivienda";
        SpannableStringBuilder strVisitas = new SpannableStringBuilder(txtVisitas);
        strVisitas.setSpan(new StyleSpan(Typeface.BOLD), 0, visitas.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sobreNosotros.setTxtVisitas(strVisitas);

        sobreNosotros.setImagenVendemos(R.drawable.vendido);

        String vendemos = "VENDEMOS";
        String txtVendemos = vendemos + "\n9 de cada 10 viviendas";
        SpannableStringBuilder strVendemos = new SpannableStringBuilder(txtVendemos);
        strVendemos.setSpan(new StyleSpan(Typeface.BOLD), 0, vendemos.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sobreNosotros.setTxtVendemos2(strVendemos);

        sobreNosotros.setImagenPersona(R.drawable.familia);

        String familias = "2000 FAMILIAS";
        String txtFamilias = familias + "\nSatisfechas con su venta";
        SpannableStringBuilder strFamilias = new SpannableStringBuilder(txtFamilias);
        strFamilias.setSpan(new StyleSpan(Typeface.BOLD), 0, familias.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sobreNosotros.setTxtPersona(strFamilias);
    }

    public static SobreNosotros getSobreNosotros() {
        return sobreNosotros;
    }
}
