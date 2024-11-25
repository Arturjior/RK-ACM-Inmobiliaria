/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.contacto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rkacm.rk_acm_inmobiliaria.R;

/*
    Clase ContactoFragment que extiende de fragment donde creamos la vista
    para obtener los datos de la clase ContactoInfo, crear los textView, imageView,
    Checkbox y Button necesarios para setear los datos y puedan mostrarse.
 */
public class ContactoFragment extends Fragment {
    public ContactoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacto_view, container, false);

        if (getArguments() != null) {
            Contacto contacto = ContactoInfo.getContacto();

            TextView tvTelefono1 = view.findViewById(R.id.telefono1);
            TextView tvTelefono2 = view.findViewById(R.id.telefono2);
            TextView tvEmail = view.findViewById(R.id.email);
            TextView tvTitulo = view.findViewById(R.id.tvtitulo);
            TextView tvTexto = view.findViewById(R.id.tvtexto);
            TextView tvOficina = view.findViewById(R.id.oficina);
            TextView tvTituloForm = view.findViewById(R.id.tituloform);
            ImageView iconoDireccion = view.findViewById(R.id.iconodireccion);
            ImageView iconoTelefono = view.findViewById(R.id.iconotelefono);
            ImageView iconoEmail = view.findViewById(R.id.iconoemail);
            EditText edNombreForm = view.findViewById(R.id.nombreform);
            EditText edEmailForm = view.findViewById(R.id.emailform);
            EditText edTelefonoForm = view.findViewById(R.id.tlfform);
            EditText edObserForm = view.findViewById(R.id.observacionesform);
            CheckBox checkBox = view.findViewById(R.id.checkBox);
            Button btnEnviar = view.findViewById(R.id.btnEnviar);

            tvTelefono1.setText(contacto.getTelefono1());
            tvTelefono2.setText(contacto.getTelefono2());
            tvEmail.setText(contacto.getEmail());
            tvTitulo.setText(contacto.getTituloContacto());
            tvTexto.setText(contacto.getTextoContacto());
            tvOficina.setText(contacto.getOficina());
            tvTituloForm.setText(contacto.getTituloFormulario());
            iconoDireccion.setImageResource(contacto.getIconoDireccion());
            iconoTelefono.setImageResource(contacto.getIconoTelefono());
            iconoEmail.setImageResource(contacto.getIconoEmail());
            checkBox.setText(contacto.getCheckBox());
            btnEnviar.setEnabled(false);

            /*
                Añadimos un onClickListener al cual le añadimos un dialogo de alerta para
                pedir permisos al usuario para abrir el cliente de llamada para poder llamar
                directamente al numero.
             */
            tvTelefono1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Confirmación")
                            .setMessage("¿Abrir Teléfono?")
                            .setIcon(R.drawable.alerta)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String numTelefono = (String)tvTelefono1.getText();
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:" + numTelefono));
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                }
            });

            /*
                Añadimos un onClickListener al cual le añadimos un dialogo de alerta para
                pedir permisos al usuario para abrir el cliente de llamada para poder llamar
                directamente al numero.
             */
            tvTelefono2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Confirmación")
                            .setMessage("¿Abrir Teléfono?")
                            .setIcon(R.drawable.alerta)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String numTelefono = (String)tvTelefono2.getText();
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:" + numTelefono));
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                }
            });

            /*
                Añadimos un onClickListener al cual le añadimos un dialogo de alerta para
                pedir permisos al usuario para abrir el cliente de correo para poder mandar
                un email directamente.
             */
            tvEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Confirmación")
                            .setMessage("¿Abrir Correo Electrónico?")
                            .setIcon(R.drawable.alerta)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String txtEmail = (String)tvEmail.getText();

                                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                                    intent.setData(Uri.parse("mailto:"));
                                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] {txtEmail});
                                    intent.putExtra(Intent.EXTRA_SUBJECT, "Contacto RK ACM Inmobiliaria");

                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                }
            });

            /*
                Añadimos un onCheckedChangeListener para comprobar si el checkbox esta marcado o no.
             */
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    btnEnviar.setEnabled(checkBox.isChecked());
                }
            });

            /*
                Creamos un onClickListener para poder enviar los datos del formulario una vez
                pulsamos sobre el boton de enviar.
                Controlamos que los campos de nombre, email, telefono y observacion esten
                correctamente puestos y que no haya ningun error.
             */
            btnEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nombreForm = edNombreForm.getText().toString();
                    String emailForm = edEmailForm.getText().toString();
                    String telefonoForm = edTelefonoForm.getText().toString();
                    String obserForm = edObserForm.getText().toString();

                    if (nombreForm.isEmpty() || !nombreForm.matches("[a-zA-Z]+")) {
                        edNombreForm.setError("Este campo no puede estar vacío o contener numeros");
                        edNombreForm.requestFocus();
                    } else if (emailForm.isEmpty() || (!emailForm.endsWith("@gmail.com") && !emailForm.endsWith("@outlook.com") &&
                            !emailForm.endsWith("@hotmail.es") && !emailForm.endsWith("@hotmail.com"))) {
                        edEmailForm.setError("Por favor, introduce un correo electrónico válido");
                        edEmailForm.requestFocus();
                    } else if (telefonoForm.isEmpty() || !telefonoForm.matches("\\d{9}")) {
                        edTelefonoForm.setError("El teléfono debe tener exactamente 9 dígitos");
                        edTelefonoForm.requestFocus();
                    } else if (obserForm.isEmpty()) {
                        edObserForm.setError("Este campo no puede estar vacío");
                        edObserForm.requestFocus();
                    } else {
                        btnEnviar.setEnabled(true);
                        checkBox.setChecked(true);
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"info@inmobiliariaacm.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Mensaje de " + nombreForm);
                        intent.putExtra(Intent.EXTRA_TEXT, "Nombre: " + nombreForm + "\n" +
                                "Email: " + emailForm + "\n" +
                                "Teléfono: " + telefonoForm + "\n" +
                                "Observaciones: " + obserForm);

                        startActivity(intent);
                    }
                }
            });
        }

        return view;
    }
}