/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.inicio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.rkacm.rk_acm_inmobiliaria.R;
import com.rkacm.rk_acm_inmobiliaria.ui.compra.CompraFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.herencia.HerenciaFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.hipoteca.HipotecaInicioFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.venta.VentaFragment;

public class InicioFragment extends Fragment {
    Button ultimo_pulsado = null;

    public InicioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inicio_view, container, false);

        Button btnComprar = view.findViewById(R.id.btnComprar);
        Button btnVender = view.findViewById(R.id.btnVender);
        Button btnHerencia = view.findViewById(R.id.btnHerencia);
        Button btnHipoteca = view.findViewById(R.id.btnHipoteca);

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ultimo_pulsado != null) {
                    ultimo_pulsado.setTextColor(getResources().getColor(R.color.white));
                }

                btnComprar.setTextColor(getResources().getColor(R.color.coral));
                ultimo_pulsado = btnComprar;

                Fragment compraFragment = new CompraFragment();
                Bundle bundle = new Bundle();

                compraFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, compraFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ultimo_pulsado != null) {
                    ultimo_pulsado.setTextColor(getResources().getColor(R.color.white));
                }

                btnVender.setTextColor(getResources().getColor(R.color.coral));
                ultimo_pulsado = btnVender;

                Fragment ventaFragment = new VentaFragment();
                Bundle bundle = new Bundle();

                ventaFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, ventaFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnHerencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ultimo_pulsado != null) {
                    ultimo_pulsado.setTextColor(getResources().getColor(R.color.white));
                }

                btnHerencia.setTextColor(getResources().getColor(R.color.coral));
                ultimo_pulsado = btnHerencia;

                Fragment herenciaFragment = new HerenciaFragment();
                Bundle bundle = new Bundle();

                herenciaFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, herenciaFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnHipoteca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment hipotecaInicioFragment = new HipotecaInicioFragment();
                Bundle bundle = new Bundle();

                hipotecaInicioFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, hipotecaInicioFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}