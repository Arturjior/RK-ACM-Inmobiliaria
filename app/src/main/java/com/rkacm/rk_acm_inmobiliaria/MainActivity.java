/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.rkacm.rk_acm_inmobiliaria.ui.compra.CompraFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.contacto.ContactoFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.hipoteca.HipotecaInicioFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.inicio.InicioFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.login.LogeadoFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.login.LoginFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.sobreNosotros.SobreNosotrosFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.herencia.HerenciaFragment;
import com.rkacm.rk_acm_inmobiliaria.ui.venta.VentaFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button btnComprar, btnVender, btnHerencia;
    Button ultimo_pulsado = null;
    FirebaseAuth mAuth;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Fragment fr = new InicioFragment();
        Bundle bundle = new Bundle();

        fr.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, fr)
                .commit();

        btnComprar = findViewById(R.id.btnComprar);
        btnVender = findViewById(R.id.btnVender);
        btnHerencia = findViewById(R.id.btnHerencia);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Confirmación")
                        .setMessage("¿Abrir Teléfono?")
                        .setIcon(R.drawable.alerta)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String numTelefono = "669 76 72 65";
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel: " +numTelefono));

                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });

        FloatingActionButton fabWeb = (FloatingActionButton) findViewById(R.id.fabWeb);
        fabWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Confirmación")
                        .setMessage("¿Abrir Web?")
                        .setIcon(R.drawable.alerta)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String web = "https://www.inmobiliariaacm.com/";
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(web));

                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        String token = task.getResult();

                        String msg = getString(R.string.msg_token, token);
                        Log.d(TAG, msg);
                        Log.d(TAG, "FCM Token: " + token);

                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", token);
                        editor.apply();
                    }
                });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "canalNoti";
            String description = "descripcionNoti";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channelId", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_login) {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if (firebaseUser != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, new LogeadoFragment())
                        .addToBackStack(null)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        int containerId;

        containerId = R.id.content_main;

        Fragment f = null;
        Bundle bundle = new Bundle();

        if (id == R.id.nav_home) {
            if (ultimo_pulsado != null) {
                ultimo_pulsado.setTextColor(getResources().getColor(R.color.white));
            }

            f = new InicioFragment();
            bundle.putString("categoria", "home");
        } else if (id == R.id.nav_compra) {
            if (ultimo_pulsado != null) {
                ultimo_pulsado.setTextColor(getResources().getColor(R.color.white));
            }

            f = new CompraFragment();
            bundle.putString("categoria", "compra");
        } else if (id == R.id.nav_venta) {
            if (ultimo_pulsado != null) {
                ultimo_pulsado.setTextColor(getResources().getColor(R.color.white));
            }

            f = new VentaFragment();
            bundle.putString("categoria", "venta");
        } else if (id == R.id.nav_herencia) {
            if (ultimo_pulsado != null) {
                ultimo_pulsado.setTextColor(getResources().getColor(R.color.white));
            }

            f = new HerenciaFragment();
            bundle.putString("categoria", "herencias");
        } else if (id == R.id.nav_hipoteca) {
            if (ultimo_pulsado != null) {
                ultimo_pulsado.setTextColor(getResources().getColor(R.color.white));
            }

            f = new HipotecaInicioFragment();
            bundle.putString("categoria", "hipoteca");
        } else if (id == R.id.nav_sobreNosotros) {
            if (ultimo_pulsado != null) {
                ultimo_pulsado.setTextColor(getResources().getColor(R.color.white));
            }

            f = new SobreNosotrosFragment();
            bundle.putString("categoria", "sobre nosotros");
        } else if (id == R.id.nav_contacto) {
            if (ultimo_pulsado != null) {
                ultimo_pulsado.setTextColor(getResources().getColor(R.color.white));
            }

            f = new ContactoFragment();
            bundle.putString("categoria", "contacto");
        }

        if(f!=null) {
            f.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerId,f)
                    .addToBackStack(null)
                    .commit();
        }

        DrawerLayout drawer;

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}