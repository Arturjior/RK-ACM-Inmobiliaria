/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rkacm.rk_acm_inmobiliaria.R;

/*
    Clase LogeadoFragment que extiende de Fragment y se encarga de gestionar la vista
    para el estado de sesión iniciada de usuarios.
 */
public class LogeadoFragment extends Fragment {
    FirebaseAuth mAuth;

    public LogeadoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logeado, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser datosUser = mAuth.getCurrentUser();

        TextView tvEmailUser = view.findViewById(R.id.tvEmailUser);
        ImageView ivLogotipo = view.findViewById(R.id.ivLogotipo);
        Button btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);

        ivLogotipo.setImageResource(R.drawable.logorkacm);

        if (datosUser != null) {
            String emailUser = datosUser.getEmail();

            tvEmailUser.setText(emailUser);
        }

        /*
            Configurar el OnClickListener para el botón de cerrar sesión
         */
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                Toast.makeText(getActivity(), "Cierre de Sesion Exitoso", Toast.LENGTH_SHORT).show();

                Fragment loginFragment = new LoginFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_main, loginFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}