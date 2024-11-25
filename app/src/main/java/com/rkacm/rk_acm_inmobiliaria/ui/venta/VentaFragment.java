/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.venta;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
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
    Clase VentaFragment que extiende de Fragment y se encarga de gestionar
    la vista para mostrar información detallada sobre la venta de propiedades.
 */
public class VentaFragment extends Fragment {
    private static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 1;
    private static final String PDF_URL = "https://drive.google.com/file/d/1za_8SQg9-WcdfdTzI_VwUDqdmYT1qegB/view?usp=drive_link";
    private DatabaseReference reference;
    FirebaseAuth mAuth;

    public VentaFragment() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                empezarDescarga();
            } else {
                Toast.makeText(requireContext(), "Permiso de escritura externa denegado. No se puede descargar el PDF.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venta_view, container, false);
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance("https://rkacm-cc439-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        if (getArguments() != null) {
            Venta venta = VentaInfo.getVenta();

            TextView principal3 = view.findViewById(R.id.tvprincipal3);

            principal3.setOnClickListener(new View.OnClickListener() {
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
                                        datosDescarga.put("guia_venta", "La descarga de la guia de venta la ha realizado el usuario: " + user);
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

            TextView principal4 = view.findViewById(R.id.tvprincipal4);
            TextView homeStaging = view.findViewById(R.id.tvhomestaging);
            TextView reportaje = view.findViewById(R.id.tvreportaje);
            TextView escaneado = view.findViewById(R.id.tvescaneado);
            TextView reforma = view.findViewById(R.id.tvreforma);
            TextView compradores = view.findViewById(R.id.tvcompradores);
            TextView valoracion = view.findViewById(R.id.tvvaloracion);
            TextView carteles = view.findViewById(R.id.tvcarteles);
            TextView escaparates = view.findViewById(R.id.tvescaparates);
            TextView web = view.findViewById(R.id.tvweb);
            TextView portales = view.findViewById(R.id.tvportales);
            TextView colaboracion = view.findViewById(R.id.tvcolaboracion);
            TextView flyers = view.findViewById(R.id.tvflyers);

            ImageView imagenHomeStaging = view.findViewById(R.id.imagenhomestaging);
            ImageView imagenReportaje = view.findViewById(R.id.imagenreportaje);
            ImageView imagenEscaneado = view.findViewById(R.id.imagenescaneado);
            ImageView imagenReforma = view.findViewById(R.id.imagenreforma);
            ImageView imagenCompradores = view.findViewById(R.id.imagencompradores);
            ImageView imagenValoracion = view.findViewById(R.id.imagenvaloracion);
            ImageView imagenCarteles = view.findViewById(R.id.imagencarteles);
            ImageView imagenEscaparates = view.findViewById(R.id.imagenescaparates);
            ImageView imagenWeb = view.findViewById(R.id.imagenweb);
            ImageView imagenPortales = view.findViewById(R.id.imagenportales);
            ImageView imagenColaboracion = view.findViewById(R.id.imagencolaboracion);
            ImageView imagenFlyers = view.findViewById(R.id.imagenflyers);

            principal3.setText(venta.getPrincipal3());
            principal4.setText(venta.getPrincipal4());
            homeStaging.setText(venta.getHomeStaging());
            reportaje.setText(venta.getReportaje());
            escaneado.setText(venta.getEscaneado());
            reforma.setText(venta.getReforma());
            compradores.setText(venta.getCompradores());
            valoracion.setText(venta.getValoracion());
            carteles.setText(venta.getCarteles());
            escaparates.setText(venta.getEscaparates());
            web.setText(venta.getWeb());
            portales.setText(venta.getPortales());
            colaboracion.setText(venta.getColaboracion());
            flyers.setText(venta.getFlyers());
            imagenHomeStaging.setImageResource(venta.getImagenHomeStaging());
            imagenReportaje.setImageResource(venta.getImagenReportaje());
            imagenEscaneado.setImageResource(venta.getImagenEscaneado());
            imagenReforma.setImageResource(venta.getImagenReforma());
            imagenCompradores.setImageResource(venta.getImagenCompradores());
            imagenValoracion.setImageResource(venta.getImagenValoracion());
            imagenCarteles.setImageResource(venta.getImagenCarteles());
            imagenEscaparates.setImageResource(venta.getImagenEscaparates());
            imagenWeb.setImageResource(venta.getImagenWeb());
            imagenPortales.setImageResource(venta.getImagenPortales());
            imagenColaboracion.setImageResource(venta.getImagenColaboracion());
            imagenFlyers.setImageResource(venta.getImagenFlyers());
        }

        return view;
    }

    /*
        Método para comenzar la descarga del archivo PDF.
     */
    private void empezarDescarga() {
        DownloadManager downloadManager = (DownloadManager) requireActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(PDF_URL));
        request.setTitle("DIGITAL_guia_10_semanas_acm");
        request.setDescription("Descargando DIGITAL_guia_10_semanas_acm");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        downloadManager.enqueue(request);
    }
}