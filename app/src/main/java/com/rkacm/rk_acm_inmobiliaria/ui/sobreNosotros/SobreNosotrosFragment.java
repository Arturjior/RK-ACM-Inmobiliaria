/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.sobreNosotros;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rkacm.rk_acm_inmobiliaria.R;

/*
    Clase SobreNosotrosFragment que extiende de Fragment y se encarga de gestionar
    la vista para mostrar información detallada sobre la empresa.
 */
public class SobreNosotrosFragment extends Fragment {
    public SobreNosotrosFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sobre_nosotros_view, container, false);

        if (getArguments() != null) {
            SobreNosotros sobreNosotros = SobreNosotrosInfo.getSobreNosotros();

            ImageView imagenLogo = view.findViewById(R.id.imagenlogo);
            ImageView imagenGrafico1 = view.findViewById(R.id.imagenGrafico1);
            ImageView imagenPorciento = view.findViewById(R.id.imagenPorciento);
            ImageView imagenGrafico2 = view.findViewById(R.id.imagenGrafico2);
            ImageView imagenVisitas = view.findViewById(R.id.imagenVisitas);
            ImageView imagenPersona = view.findViewById(R.id.imagenPersona);
            TextView tvInformacion = view.findViewById(R.id.tvinformacion);
            TextView tvTituloNosotros = view.findViewById(R.id.tvnosotros);
            TextView tvGrafico1 = view.findViewById(R.id.tvGrafico1);
            TextView tvPorciento = view.findViewById(R.id.tvPorciento);
            TextView tvGrafico2 = view.findViewById(R.id.tvGrafico2);
            TextView tvVisitas = view.findViewById(R.id.tvVisitas);
            ImageView imagenVendido = view.findViewById(R.id.imagenVendido);
            TextView tvVendemos2 = view.findViewById(R.id.tvVendemos2);
            TextView tvPersona = view.findViewById(R.id.tvPersona);

            imagenLogo.setImageResource(sobreNosotros.getImagenLogo());
            imagenGrafico1.setImageResource(sobreNosotros.getImagenGrafico1());
            imagenPorciento.setImageResource(sobreNosotros.getImagenPorciento());
            imagenGrafico2.setImageResource(sobreNosotros.getImagenGrafico2());
            imagenVisitas.setImageResource(sobreNosotros.getImagenVisitas());
            imagenPersona.setImageResource(sobreNosotros.getImagenPersona());
            tvInformacion.setText(sobreNosotros.getInformacion());
            tvTituloNosotros.setText(sobreNosotros.getTituloNosotros());
            tvGrafico1.setText(sobreNosotros.getTxtGrafico1());
            tvPorciento.setText(sobreNosotros.getTxtPorciento());
            tvGrafico2.setText(sobreNosotros.getTxtGrafico2());
            tvVisitas.setText(sobreNosotros.getTxtVisitas());
            imagenVendido.setImageResource(sobreNosotros.getImagenVendemos());
            tvVendemos2.setText(sobreNosotros.getTxtVendemos2());
            tvPersona.setText(sobreNosotros.getTxtPersona());
        }

        return view;
    }
}