/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.compra;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rkacm.rk_acm_inmobiliaria.Entidades.Vivienda;
import com.rkacm.rk_acm_inmobiliaria.R;
import com.rkacm.rk_acm_inmobiliaria.databinding.FragmentCompraBinding;
import com.rkacm.rk_acm_inmobiliaria.ui.masInfo.LeerMasFragment;
import com.squareup.picasso.Picasso;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
    Clase CompraFragment que extiende de Fragment y se encarga de gestionar la vista
    para mostrar y filtrar los datos de las viviendas disponibles para compra.
 */
public class CompraFragment extends Fragment {
    private FragmentCompraBinding binding;
    private DatabaseReference reference;
    private StorageReference referenceimage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCompraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Compra compra = CompraInfo.getCompra();

        Spinner tipovivienda = root.findViewById(R.id.spinner_tipovivienda);
        Spinner ordenacion = root.findViewById(R.id.spinner_ordenacion);
        Spinner localidad = root.findViewById(R.id.spinner_localidad);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,compra.getTipovivienda());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipovivienda.setAdapter(adapter);

        ArrayAdapter<String> adapterordenacion = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,compra.getOrdenacion());
        adapterordenacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ordenacion.setAdapter(adapterordenacion);

        ArrayAdapter<String> adapterlocalidad = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,compra.getLocalidad());
        adapterlocalidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localidad.setAdapter(adapterlocalidad);

        reference = FirebaseDatabase.getInstance("https://rkacm-cc439-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        /*
            Obtener y mostrar todas las viviendas al iniciar el fragmento
         */
        reference.child("Viviendas").orderByChild("referencia").addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LinearLayout resultados = root.findViewById(R.id.datos);
                resultados.removeAllViews();

                List<DataSnapshot> viviendas = new ArrayList<>();

                for (DataSnapshot viviendasSnapshot : dataSnapshot.getChildren()) {
                    viviendas.add(viviendasSnapshot);
                }

                Collections.sort(viviendas, new Comparator<DataSnapshot>() {
                    @Override
                    public int compare(DataSnapshot vivienda1, DataSnapshot vivienda2) {
                        Integer antiguedad1 = vivienda1.child("id").getValue(Integer.class);
                        Integer antiguedad2 = vivienda2.child("id").getValue(Integer.class);
                        return Integer.compare(antiguedad2, antiguedad1);
                    }
                });

                for (DataSnapshot viviendaSnapshot : viviendas) {
                    datosCompra(viviendaSnapshot, resultados);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error al obtener datos: " + error.getMessage());
            }
        });

        /*
            Botón para buscar viviendas según los filtros seleccionados
         */
        ImageView busca = root.findViewById(R.id.buscar);
        busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vivienda = tipovivienda.getSelectedItem().toString();
                String ordenacionelegida = ordenacion.getSelectedItem().toString();
                String localidadelegida = localidad.getSelectedItem().toString();

                reference = FirebaseDatabase.getInstance("https://rkacm-cc439-default-rtdb.europe-west1.firebasedatabase.app").getReference();
                reference.child("Viviendas").addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        LinearLayout resultados = root.findViewById(R.id.datos);
                        resultados.removeAllViews();

                        ArrayList<DataSnapshot> viviendas = new ArrayList<DataSnapshot>();
                        for (DataSnapshot viviendaSnapshot : dataSnapshot.getChildren()) {
                            String tipoPropiedad = viviendaSnapshot.child("tipo propiedad").getValue(String.class);
                            String localidadVivienda = viviendaSnapshot.child("localidad").getValue(String.class);

                            String filtroTipo = vivienda.isEmpty() ? "Tipo" : vivienda;
                            String filtroLocalidad = localidadelegida.isEmpty() ? "Localidad" : localidadelegida;

                            boolean tipoFiltrado = (tipoPropiedad != null && tipoPropiedad.equals(filtroTipo)) || filtroTipo.equals("Tipo");
                            boolean localidadFiltrada = (localidadVivienda != null && localidadVivienda.equals(filtroLocalidad)) || filtroLocalidad.equals("Localidad");

                            if (tipoFiltrado && localidadFiltrada) {
                                viviendas.add(viviendaSnapshot);
                            }
                        }

                        viviendas.sort(new Comparator<DataSnapshot>() {
                            @Override
                            public int compare(DataSnapshot vivienda1, DataSnapshot vivienda2) {
                                Integer id1 = vivienda1.child("id").getValue(Integer.class);
                                Integer id2 = vivienda2.child("id").getValue(Integer.class);
                                return Integer.compare(id2, id1);
                            }
                        });

                        switch (ordenacionelegida) {
                            case "Más reciente":
                                Collections.sort(viviendas, new Comparator<DataSnapshot>() {
                                    @Override
                                    public int compare(DataSnapshot vivienda1, DataSnapshot vivienda2) {
                                        Integer antiguedad1 = vivienda1.child("id").getValue(Integer.class);
                                        Integer antiguedad2 = vivienda2.child("id").getValue(Integer.class);
                                        return Integer.compare(antiguedad2, antiguedad1);
                                    }
                                });

                                break;
                            case "Más antiguo":
                                viviendas.sort(new Comparator<DataSnapshot>() {
                                    @Override
                                    public int compare(DataSnapshot vivienda1, DataSnapshot vivienda2) {
                                        Integer antiguedad1 = vivienda1.child("id").getValue(Integer.class);
                                        Integer antiguedad2 = vivienda2.child("id").getValue(Integer.class);
                                        return Integer.compare(antiguedad1, antiguedad2);
                                    }
                                });
                                break;
                            case "Precio venta más alto":
                                viviendas.sort(new Comparator<DataSnapshot>() {
                                    @Override
                                    public int compare(DataSnapshot vivienda1, DataSnapshot vivienda2) {
                                        Integer precio1 = vivienda1.child("precio").getValue(Integer.class);
                                        Integer precio2 = vivienda2.child("precio").getValue(Integer.class);
                                        return Integer.compare(precio2, precio1);
                                    }
                                });
                                break;
                            case "Precio venta más bajo":
                                viviendas.sort(new Comparator<DataSnapshot>() {
                                    @Override
                                    public int compare(DataSnapshot vivienda1, DataSnapshot vivienda2) {
                                        Integer precio1 = vivienda1.child("precio").getValue(Integer.class);
                                        Integer precio2 = vivienda2.child("precio").getValue(Integer.class);
                                        return Integer.compare(precio1, precio2);
                                    }
                                });
                                break;
                        }

                        for (DataSnapshot viviendaSnapshot : viviendas) {
                            datosCompra(viviendaSnapshot, resultados);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        System.err.println("Error al obtener datos: " + error.getMessage());
                    }
                });
            }
        });

        /*
            Botón para borrar los filtros y mostrar todas las viviendas
         */
        Button borrar = root.findViewById(R.id.borra);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinnerTipovivienda = root.findViewById(R.id.spinner_tipovivienda);
                Spinner spinnerOrdenacion = root.findViewById(R.id.spinner_ordenacion);
                Spinner spinnerLocalidad = root.findViewById(R.id.spinner_localidad);
                spinnerTipovivienda.setSelection(0);
                spinnerOrdenacion.setSelection(0);
                spinnerLocalidad.setSelection(0);

                reference.child("Viviendas").orderByChild("referencia").addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        LinearLayout resultados = root.findViewById(R.id.datos);
                        resultados.removeAllViews();

                        List<DataSnapshot> viviendas = new ArrayList<>();

                        for (DataSnapshot viviendasSnapshot : dataSnapshot.getChildren()) {
                            viviendas.add(viviendasSnapshot);
                        }

                        Collections.sort(viviendas, new Comparator<DataSnapshot>() {
                            @Override
                            public int compare(DataSnapshot vivienda1, DataSnapshot vivienda2) {
                                Integer antiguedad1 = vivienda1.child("id").getValue(Integer.class);
                                Integer antiguedad2 = vivienda2.child("id").getValue(Integer.class);
                                return Integer.compare(antiguedad2, antiguedad1);
                            }
                        });

                        for (DataSnapshot viviendaSnapshot : viviendas) {
                            datosCompra(viviendaSnapshot, resultados);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.err.println("Error al obtener datos: " + error.getMessage());
                    }
                });
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /*
        Método para obtener los datos de compra de una vivienda y mostrarlos en la vista.
     */
    private void datosCompra(DataSnapshot viviendaSnapshot, LinearLayout resultados) {
        DataSnapshot referenciaSnapshot = viviendaSnapshot.child("referencia");
        if (referenciaSnapshot.exists()) {
            String ref = referenciaSnapshot.getValue(String.class);
            if (ref != null) {
                String nombre = viviendaSnapshot.child("nombre").getValue(String.class);
                Integer precio = viviendaSnapshot.child("precio").getValue(Integer.class);
                String referencia = viviendaSnapshot.child("referencia").getValue(String.class);
                Vivienda vivienda = new Vivienda(ref, nombre, precio, ref.toString(), referencia);

                TextView textViewNombre = new TextView(getContext());
                TextView textViewPrecio = new TextView(getContext());
                TextView textViewReferencia = new TextView(getContext());
                ImageView imagen = new ImageView(getContext());
                Button botonInfo = new Button(getContext());

                String nombreTexto = vivienda.getNombre();
                SpannableStringBuilder strNombre = new SpannableStringBuilder(nombreTexto);
                strNombre.setSpan(new StyleSpan(Typeface.BOLD), 0, nombreTexto.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textViewNombre.setText(strNombre);

                String precioTexto = "Precio: ";
                String totalTexto = precioTexto + precio + " €";
                SpannableStringBuilder strPrecio = new SpannableStringBuilder(totalTexto);
                strPrecio.setSpan(new StyleSpan(Typeface.BOLD), 0, precioTexto.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textViewPrecio.setText(strPrecio);

                String refTexto = "Ref: ";
                String referenciaTexto = refTexto + referencia;
                SpannableStringBuilder strRef = new SpannableStringBuilder(referenciaTexto);
                strRef.setSpan(new StyleSpan(Typeface.BOLD), 0, refTexto.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textViewReferencia.setText(strRef);

                referenceimage = FirebaseStorage.getInstance("gs://rkacm-cc439.appspot.com").getReference("images/" + vivienda.getReferencia() + ".jpg");
                referenceimage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get()
                                .load(uri.toString())
                                .into(imagen);
                    }
                });

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, 10);
                imagen.setLayoutParams(layoutParams);

                int maxWidth = 300;
                int maxHeight = 400;

                imagen.setMinimumWidth(maxWidth);
                imagen.setMinimumHeight(maxHeight);
                imagen.setAdjustViewBounds(true);
                imagen.setScaleType(ImageView.ScaleType.FIT_START);

                botonInfo.setText("LEER MÁS");
                botonInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fr = new LeerMasFragment();
                        Bundle bd = new Bundle();
                        bd.putString("ref_vivienda", ref);
                        fr.setArguments(bd);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content_main, fr);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });

                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                botonInfo.setLayoutParams(layoutParams1);

                resultados.addView(imagen);
                resultados.addView(textViewNombre);
                resultados.addView(textViewPrecio);
                resultados.addView(textViewReferencia);
                resultados.addView(botonInfo);
                layoutParams1.setMargins(0, 0, 0, 80);
            }
        }
    }
}