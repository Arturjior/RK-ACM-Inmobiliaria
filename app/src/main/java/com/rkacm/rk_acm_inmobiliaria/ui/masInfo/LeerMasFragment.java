/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.masInfo;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.rkacm.rk_acm_inmobiliaria.R;
import com.rkacm.rk_acm_inmobiliaria.ui.hipoteca.HipotecaFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/*
    Clase LeerMasFragment que extiende de Fragment y se encarga de gestionar la vista
    para mostrar información detallada sobre una vivienda y ofrecer la opción de calcular
    una hipoteca para dicha vivienda.
 */
public class LeerMasFragment extends Fragment {
    private DatabaseReference reference;
    private Integer precio;

    public LeerMasFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.leermas_view, container, false);

        Bundle args = getArguments();
        if (args != null) {
            String numero = args.getString("ref_vivienda");
            reference = FirebaseDatabase.getInstance("https://rkacm-cc439-default-rtdb.europe-west1.firebasedatabase.app").getReference();

            reference.child("Viviendas").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot viviendaSnapshot : snapshot.getChildren()) {
                        DataSnapshot idSnapshot = viviendaSnapshot.child("referencia");
                        if (idSnapshot.exists()) {
                            String ref = idSnapshot.getValue(String.class);
                            if (ref.equals(numero)) {
                                String descripcion = viviendaSnapshot.child("descripcion").getValue(String.class);
                                String referencia = viviendaSnapshot.child("referencia").getValue(String.class);
                                String tipoPropiedad = viviendaSnapshot.child("tipo propiedad").getValue(String.class);
                                String zonaCiudad = viviendaSnapshot.child("zona").getValue(String.class);
                                String superficieUtil = viviendaSnapshot.child("superficie util").getValue(String.class);
                                String superficieConstruida = viviendaSnapshot.child("superficie construida").getValue(String.class);
                                String conservacion = viviendaSnapshot.child("conservacion").getValue(String.class);
                                Integer habitaciones = viviendaSnapshot.child("habitaciones").getValue(Integer.class);
                                Integer banyos = viviendaSnapshot.child("baños").getValue(Integer.class);
                                String garaje = viviendaSnapshot.child("garaje").getValue(String.class);
                                Integer antiguedad = viviendaSnapshot.child("antiguedad").getValue(Integer.class);
                                Integer numPlantas = viviendaSnapshot.child("nº de plantas").getValue(Integer.class);
                                Integer planta = viviendaSnapshot.child("planta").getValue(Integer.class);
                                String consumoEnergia = viviendaSnapshot.child("consumo de energia").getValue(String.class);
                                String emisionesCO2 = viviendaSnapshot.child("emisiones co2").getValue(String.class);
                                Integer edPrecio = viviendaSnapshot.child("precio").getValue(Integer.class);

                                TextView tvDescripcion = view.findViewById(R.id.tvDescripcion);
                                TextView tvReferencia = view.findViewById(R.id.tvReferencia);
                                TextView tvTipoPropiedad = view.findViewById(R.id.tvTipoPropiedad);
                                TextView tvZonaCiudad = view.findViewById(R.id.tvZonaCiudad);
                                TextView tvSuperficieUtil = view.findViewById(R.id.tvSuperficieUtil);
                                TextView tvSuperficieConstruida = view.findViewById(R.id.tvSuperficieConstruida);
                                TextView tvConservacion = view.findViewById(R.id.tvConservacion);
                                TextView tvHabitaciones = view.findViewById(R.id.tvHabitaciones);
                                TextView tvBanyos = view.findViewById(R.id.tvBanyos);
                                TextView tvGaraje = view.findViewById(R.id.tvGaraje);
                                TextView tvAntiguedad = view.findViewById(R.id.tvAntiguedad);
                                TextView tvNumPlantas = view.findViewById(R.id.tvNumPlantas);
                                TextView tvPlanta = view.findViewById(R.id.tvPlanta);
                                TextView tvConsumoEnergia = view.findViewById(R.id.tvConsumoEnergia);
                                TextView tvEmisionesCO2 = view.findViewById(R.id.tvEmisionesCO2);

                                tvDescripcion.setText(Html.fromHtml(descripcion));
                                tvReferencia.setText(String.valueOf(referencia));
                                tvTipoPropiedad.setText(tipoPropiedad);
                                tvZonaCiudad.setText(zonaCiudad);
                                tvSuperficieUtil.setText(superficieUtil);
                                tvSuperficieConstruida.setText(superficieConstruida);
                                tvConservacion.setText(conservacion);
                                tvHabitaciones.setText(String.valueOf(habitaciones));
                                tvBanyos.setText(String.valueOf(banyos));
                                tvGaraje.setText(garaje);
                                tvAntiguedad.setText(String.valueOf(antiguedad));
                                tvNumPlantas.setText(String.valueOf(numPlantas));
                                tvPlanta.setText(String.valueOf(planta));
                                tvConsumoEnergia.setText(String.valueOf(consumoEnergia));
                                tvEmisionesCO2.setText(String.valueOf(emisionesCO2));
                                precio = edPrecio;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            ImageSlider imageSlider = view.findViewById(R.id.imgSlider);
            final int[] imageIndex = {0};

            FirebaseStorage storage = FirebaseStorage.getInstance("gs://rkacm-cc439.appspot.com");
            StorageReference listaRef = storage.getReference().child("images").child(numero);
            ArrayList<SlideModel> urlImagenes = new ArrayList<>();

            listaRef.listAll()
                    .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                          @Override
                          public void onSuccess(ListResult listResult) {
                              for (StorageReference item : listResult.getItems()) {
                                  item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                      @Override
                                      public void onSuccess(Uri uri) {
                                          urlImagenes.add(new SlideModel(uri.toString(), ScaleTypes.FIT));

                                          if (urlImagenes.size() == listResult.getItems().size()) {
                                              imageSlider.setImageList(urlImagenes, ScaleTypes.FIT);
                                          }

                                          imageSlider.setItemClickListener(new ItemClickListener() {
                                              @Override
                                              public void onItemSelected(int i) {
                                                  mostrarImagen(urlImagenes.get(i).getImageUrl());
                                              }

                                              @Override
                                              public void doubleClick(int i) {}
                                          });
                                      }
                                  });
                              }
                          }
                      });

            Button btnHipoteca = view.findViewById(R.id.btnHipoteca);

            /*
                Configurar el botón para calcular la hipoteca
             */
            btnHipoteca.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment hipotecaFragment = new HipotecaFragment();
                    Bundle bundle = new Bundle();

                    bundle.putString("precio", String.valueOf(precio));
                    hipotecaFragment.setArguments(bundle);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, hipotecaFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        return view;
    }

    /*
        Metodo para mostrar la imagen en un dialogo
     */
    private void mostrarImagen(String url) {
        Dialog dialogo = new Dialog(getContext());
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setContentView(R.layout.dialogoimagen);

        PhotoView imagenGrande = dialogo.findViewById(R.id.imagenGrande);
        Picasso.get().load(url).into(imagenGrande);
        dialogo.show();
    }
}