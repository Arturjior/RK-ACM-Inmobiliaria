/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rkacm.rk_acm_inmobiliaria.MainActivity;
import com.rkacm.rk_acm_inmobiliaria.R;

import java.util.HashMap;
import java.util.Map;

/*
    Clase LoginFragment que extiende de Fragment y se encarga de gestionar la vista
    para el inicio de sesión de usuarios.
 */
public class LoginFragment extends Fragment {
    private EditText edEmail, edPassword;
    private String email = "";
    private String password = "";
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.webClientId))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        mAuth = FirebaseAuth.getInstance();

        edEmail = view.findViewById(R.id.email);
        edPassword = view.findViewById(R.id.password);
        ImageView ivLogotipo = view.findViewById(R.id.ivLogotipo);
        Button btnInicio = view.findViewById(R.id.login);
        Button btnRegistro = view.findViewById(R.id.signin);
        SignInButton btnGoogle = view.findViewById(R.id.googleInicio);

        /*
            Configurar el OnClickListener para el botón de inicio de sesión
         */
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edEmail.getText().toString();
                password = edPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    if (password.length() >= 8 && password.matches(".*[a-zA-Z].*")) {
                        iniciarSesion();
                    } else {
                        Toast.makeText(getActivity(), "La contraseña debe tener al menos 8 caracteres entre letras y numeros", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Debe rellenar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
            Configurar el OnClickListener para el botón de registro
         */
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment signinFragment = new SigninFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_main, signinFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        /*
            Configurar el OnClickListener para el botón de inicio de sesión con Google
         */
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        ivLogotipo.setImageResource(R.drawable.logorkacm);

        return view;
    }

    /*
        Método para iniciar sesión con email y contraseña.
     */
    private void iniciarSesion() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Email o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void iniciarSesionGoogle(FirebaseUser user) {
        String userName = user.getDisplayName();
        String userEmail = user.getEmail();

        Map<String, Object> newUser = new HashMap<>();
        newUser.put("nombre", userName);
        newUser.put("email", userEmail);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(getActivity(), "Inicio de sesión con Google fallido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            boolean isNewUser = task.getResult().getAdditionalUserInfo().isNewUser();

                            if (isNewUser) {
                                iniciarSesionGoogle(user);
                            }

                            Toast.makeText(getActivity(), "Inicio de sesión con Google exitoso", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            Toast.makeText(getActivity(), "Inicio de sesión con Google fallido", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    /*
        Método para iniciar sesión con Google.
     */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /*
        Método para actualizar la interfaz de usuario después del inicio de sesión.
     */
    @SuppressLint("RestrictedApi")
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(getActivity(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }
}