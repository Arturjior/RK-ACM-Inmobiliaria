/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.login;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.rkacm.rk_acm_inmobiliaria.MainActivity;
import com.rkacm.rk_acm_inmobiliaria.R;

import java.util.HashMap;
import java.util.Map;

/*
    Clase SigninFragment que extiende de Fragment y se encarga de gestionar
    la vista para el registro de nuevos usuarios.
 */
public class SigninFragment extends Fragment {
    private EditText edUsuario, edEmail, edPassword;
    private String nombre = "";
    private String email = "";
    private String password = "";
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    public SigninFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://rkacm-cc439-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        mAuth = FirebaseAuth.getInstance();

        edUsuario = view.findViewById(R.id.nombre);
        edEmail = view.findViewById(R.id.email);
        edPassword = view.findViewById(R.id.password);
        ImageView ivLogotipo = view.findViewById(R.id.ivLogotipo);
        Button btnInicio = view.findViewById(R.id.login);
        Button btnRegistro = view.findViewById(R.id.signin);

        /*
            Configurar el OnClickListener para el botón de inicio de sesión
         */
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment loginFragment = new LoginFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_main, loginFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        /*
            Configurar el OnClickListener para el botón de registro
         */
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = edUsuario.getText().toString();
                email = edEmail.getText().toString();
                password = edPassword.getText().toString();

                if (!nombre.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    if (password.length() >= 8 && password.matches(".*[a-zA-Z].*")) {
                        registrarUsuario();
                    } else {
                        Toast.makeText(getActivity(), "La contraseña debe tener al menos 8 caracteres entre letras y numeros", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Debe rellenar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivLogotipo.setImageResource(R.drawable.logorkacm);

        return view;
    }

    /*
        Método para registrar un nuevo usuario en Firebase Authentication. Y verificar si el correo
        es de Gmail, Outlook o Hotmail.
     */
    private void registrarUsuario() {
        if (email.endsWith("@gmail.com") || email.endsWith("@outlook.com") || email.endsWith("@hotmail.es")
                || email.endsWith("@hotmail.com")) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("nombre", nombre);
                        map.put("email", email);
                        map.put("password", password);

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getActivity(), "No se pudieron crear los datos", Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(getActivity(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        String errorMessage = task.getException().getMessage();
                        Toast.makeText(getActivity(), "Error: El email ya esta en uso con otra cuenta", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getActivity(), "Por favor, introduce un correo de Gmail, Outlook o Hotmail", Toast.LENGTH_SHORT).show();
        }
    }
}