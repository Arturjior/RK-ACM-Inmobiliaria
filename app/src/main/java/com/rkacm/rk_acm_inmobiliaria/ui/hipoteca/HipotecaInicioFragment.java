/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.hipoteca;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.rkacm.rk_acm_inmobiliaria.Entidades.Hipoteca;
import com.rkacm.rk_acm_inmobiliaria.R;

/*
    Clase HipotecaInicioFragment que extiende de Fragment y se encarga de gestionar la vista
    relacionada con la información de la hipoteca y la calculadora de hipoteca.
 */
public class HipotecaInicioFragment extends Fragment {
    EditText edPrecio, edAhorro, edTipo, edAnyos;
    TextView tvPrecImp, edImpuesto, tvCuota;
    Button btnCalcular, btnLlamar;
    SeekBar sbAhorro, sbAnyos;
    private final Hipoteca hipoteca = new Hipoteca();

    public HipotecaInicioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hipoteca_inicio_view, container, false);

        edPrecio = view.findViewById(R.id.edPrecio);
        edImpuesto = view.findViewById(R.id.edImpuesto);
        tvPrecImp = view.findViewById(R.id.tvPrecImp2);
        edAhorro = view.findViewById(R.id.edAhorro);
        edTipo = view.findViewById(R.id.edInteres);
        edAnyos = view.findViewById(R.id.edAnyos);
        tvCuota = view.findViewById(R.id.tvCuota2);
        btnCalcular = view.findViewById(R.id.btnCalcular);
        sbAhorro = view.findViewById(R.id.sbAhorro);
        sbAnyos = view.findViewById(R.id.sbAnyos);

        edPrecio.setText("0");
        edPrecio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String precio = edPrecio.getText().toString();

                    if (precio.matches("[a-zA-Z]+")) {
                        edPrecio.setText("0");
                    } else if (!precio.isEmpty() && Long.parseLong(precio) > 999999) {
                        edPrecio.setText("999999");
                    }
                }
            }
        });

        edPrecio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                calcularGastos();
                calcularPrecioImpuesto();
            }
        });

        edAhorro.setText("0");
        edAhorro.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edAhorro.getText().toString().matches("[a-zA-Z]+")){
                    edAhorro.setText("0");
                    sbAhorro.setProgress(0);
                }else{
                    if (Integer.parseInt(edAhorro.getText().toString())<sbAhorro.getMax()){
                        sbAhorro.setProgress(Integer.parseInt(edAhorro.getText().toString()));
                    }else {
                        edAhorro.setText("400000");
                        sbAhorro.setProgress(400000);
                    }
                }
            }
        });

        sbAhorro.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edAhorro.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        edTipo.setText("2");
        edTipo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String tipo = edTipo.getText().toString();

                    if (tipo.matches("[a-zA-Z]+")) {
                        edTipo.setText("2");
                    }
                }
            }
        });

        edAnyos.setText("30");
        sbAnyos.setProgress(30);
        edAnyos.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if (edAnyos.getText().toString().matches("[a-zA-Z]+")){
                        edAnyos.setText("0");
                        sbAnyos.setProgress(0);
                    }else{
                        if (Integer.parseInt(edAnyos.getText().toString())<sbAnyos.getMax()){
                            sbAnyos.setProgress(Integer.parseInt(edAnyos.getText().toString()));
                        }else {
                            edAnyos.setText("30");
                            sbAnyos.setProgress(30);
                        }
                    }
                }
            }
        });

        sbAnyos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edAnyos.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        tvCuota.setText("0");
        /*tvCuota.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cuota = tvCuota.getText().toString().replace(",", ".");

                if (cuota.matches("[a-zA-Z]+")) {
                    tvCuota.setText("0");
                } else if (!cuota.isEmpty() && Double.parseDouble(cuota) > 10000) {
                    tvCuota.setText("10000");
                }

                //calcularAnyos();
            }
        });*/

        /*
            Metodo setOnClickListener que calcula la cuota una vez hemos rellenado los datos
         */
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anyos = edAnyos.getText().toString();
                if (!anyos.isEmpty() && Long.parseLong(anyos) > 30) {
                    edAnyos.setText("30");
                }
                calcularCuota();
            }
        });

        return view;
    }

    /*
        Método para calcular los gastos con impuesto y precio.
     */
    private void calcularGastos() {
        String precioStr = edPrecio.getText().toString();

        if (!precioStr.isEmpty()) {
            double precio = Double.parseDouble(precioStr);
            double impuesto = 10;

            double resultadoGastos = precio * impuesto / 100;

            edImpuesto.setText(String.valueOf((int) Math.round(resultadoGastos)));
        }
    }

    /*
        Método para calcular el precio con impuesto y actualizar la vista.
     */
    private void calcularPrecioImpuesto() {
        String precioStr = edPrecio.getText().toString();

        if (!precioStr.isEmpty()) {
            double precio = Double.parseDouble(precioStr);
            double impuesto = 10;

            double precImp = precio + (precio * impuesto / 100);

            tvPrecImp.setText(String.valueOf((int) Math.round(precImp)));
        }
    }

    /*
        Método para calcular la cuota de la hipoteca y actualizar la vista.
     */
    private void calcularCuota() {
        String precioStr = tvPrecImp.getText().toString();
        String ahorroStr = edAhorro.getText().toString();
        String tipoStr = edTipo.getText().toString();
        String anyosStr = edAnyos.getText().toString();

        if (!precioStr.isEmpty() && !ahorroStr.isEmpty() && !tipoStr.isEmpty() && !anyosStr.isEmpty()) {
            double precio = Double.parseDouble(precioStr) - Double.parseDouble(ahorroStr);
            double tipo = Double.parseDouble(tipoStr) / 100 / 12;
            int anyos = Integer.parseInt(anyosStr);

            double cuota = (tipo * precio) / (1 - Math.pow(1 + tipo, -anyos * 12));

            tvCuota.setText(String.valueOf((int) Math.round(cuota)));
        }
    }

    /*private void calcularAnyos() {
        String precioStr = tvPrecImp.getText().toString().replace(",", ".");
        String tipoStr = edTipo.getText().toString().replace(",", ".");
        String cuotaStr = edCuota.getText().toString().replace(",", ".");

        if (!precioStr.isEmpty() && !tipoStr.isEmpty() && !cuotaStr.isEmpty()) {
            double precio = Double.parseDouble(precioStr);
            double tipo = Double.parseDouble(tipoStr) / 100 / 12;
            double cuota = Double.parseDouble(cuotaStr);

            if (cuota > tipo * precio) {
                edAnyos.setText("30");
            } else if (cuota < tipo * precio / (Math.pow(1 + tipo, 12 * 30) - 1)) {
                edAnyos.setText("0");
            } else {
                double anyos = -Math.log(1 - ((tipo * precio) / cuota)) / (12 * Math.log(1 + tipo));
                edAnyos.setText(String.valueOf((int) anyos));
            }
        }
    }*/
}