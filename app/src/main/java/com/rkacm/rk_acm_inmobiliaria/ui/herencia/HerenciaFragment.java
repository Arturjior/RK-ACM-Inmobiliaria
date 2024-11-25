/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.herencia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rkacm.rk_acm_inmobiliaria.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/*
    Clase HerenciaFragment que extiende de Fragment y se encarga de gestionar la vista
    para mostrar información sobre la herencia y ofrecer la descarga de una guía de herencia.
 */
public class HerenciaFragment extends Fragment {
    private static final String PDF_URL = "https://drive.google.com/file/d/1dpLC_0qTOUWratFlRPPzZ_YoSkFY9v36/view?usp=drive_link";
    private DatabaseReference reference;
    FirebaseAuth mAuth;

    public HerenciaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.herencia_view, container, false);
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance("https://rkacm-cc439-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        if (getArguments() != null) {
            Herencia herencia = HerenciaInfo.getHerencia();

            TextView informacion = view.findViewById(R.id.tvHerencia);
            ImageView imgAhorra = view.findViewById(R.id.imagenAhorra);
            ImageView imgNoAdelantes = view.findViewById(R.id.imagenNoAdelantes);
            ImageView imgOlvida = view.findViewById(R.id.imagenOlvida);
            ImageView imgTranquilidad = view.findViewById(R.id.imagenTranquilidad);
            TextView ahorra = view.findViewById(R.id.tvAhorra);
            TextView noAdelantes = view.findViewById(R.id.tvNoAdelantes);
            TextView olvida = view.findViewById(R.id.tvOlvida);
            TextView tranquilidad = view.findViewById(R.id.tvTranquilidad);
            TextView btnDescargaHerencia = view.findViewById(R.id.btnDescargaHerencia);

            /*
                Configurar el botón para descargar la guía de herencia
             */
            btnDescargaHerencia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Confirmación")
                            .setMessage("Para Descargar el Archivo debe Aceptar la Politica de Privacidad de la Empresa.")
                            .setIcon(R.drawable.alerta)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    if (mAuth.getCurrentUser() != null) {
                                        String user = mAuth.getCurrentUser().getEmail();
                                        SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                                        String fecha = fechaFormato.format(new Date());
                                        Map<String, Object> datosDescarga = new HashMap<>();
                                        datosDescarga.put("guia_herencia", "La descarga de la guia de herencia la ha realizado el usuario: " + user);
                                        datosDescarga.put("fecha", fecha);
                                        reference.child("Descargas").push().setValue(datosDescarga);

                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(PDF_URL));
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(v.getContext(), "Debe de Iniciar Sesion para Descargar el Archivo", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                }
            });

            informacion.setText(herencia.getInformacion());
            imgAhorra.setImageResource(herencia.getImgAhorra());
            imgNoAdelantes.setImageResource(herencia.getImgNoAdelantes());
            imgOlvida.setImageResource(herencia.getImgOlvida());
            imgTranquilidad.setImageResource(herencia.getImgTranquilidad());
            ahorra.setText(herencia.getAhorra());
            noAdelantes.setText(herencia.getNoAdelantes());
            olvida.setText(herencia.getOlvida());
            tranquilidad.setText(herencia.getTranquilidad());
            btnDescargaHerencia.setText(herencia.getGuiaHerencia());
        }

        return view;
    }
}