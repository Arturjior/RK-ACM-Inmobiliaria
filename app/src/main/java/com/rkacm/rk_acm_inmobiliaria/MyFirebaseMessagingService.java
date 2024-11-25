/**
 * Created by Arturo Jimenez Ortuño and Gabriel Denia Moreno
 */
package com.rkacm.rk_acm_inmobiliaria;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.rkacm.rk_acm_inmobiliaria.ui.compra.CompraFragment;

import java.util.HashMap;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getNotification() != null) {
            RemoteMessage.Notification notification = remoteMessage.getNotification();
            showNotificaction(notification.getTitle(), notification.getBody());
        }

        if (remoteMessage.getData().size() > 0) {
            Map<String, String> datos = remoteMessage.getData();
            String vivienda = datos.get("Viviendas");

            showNotificaction("Nueva vivienda disponible", "Se ha añadido una nueva vivienda: " + vivienda);
        }

        if (remoteMessage.getNotification() != null) {

        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String savedToken = sharedPreferences.getString("token", "");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser usuario = mAuth.getCurrentUser();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://rkacm-cc439-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        if (usuario != null) {
            databaseReference.child("Tokens").push().setValue(savedToken);
        } else {
            databaseReference.child("Tokens").push().setValue(savedToken);
        }

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("token", token);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("DeviceTokens").document().set(tokenData);
    }

    private void showNotificaction(String titulo, String mensaje) {
        Intent intent = new Intent(this, CompraFragment.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channelId")
                .setSmallIcon(R.drawable.logoinicioblanco)
                .setContentTitle(titulo)
                .setContentText(mensaje)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
