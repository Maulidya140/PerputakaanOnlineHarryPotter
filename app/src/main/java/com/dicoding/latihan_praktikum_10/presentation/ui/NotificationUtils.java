package com.dicoding.latihan_praktikum_10.presentation.ui;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.dicoding.latihan_praktikum_10.R;

public class NotificationUtils {

    public static final String CHANNEL_ID = "library_channel";

    // Membuat channel notifikasi (diperlukan untuk Android 8 ke atas)
    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Perpustakaan Channel";
            String description = "Channel untuk notifikasi aplikasi Perpustakaan Harry Potter";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    // Menampilkan notifikasi selamat datang
    public static void showWelcomeNotification(Context context) {
        // Untuk Android 13+ (Tiramisu), perlu izin POST_NOTIFICATIONS
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                // Jika tidak diizinkan, jangan lanjutkan
                return;
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_buku) // pastikan ini tersedia di drawable
                .setContentTitle("Selamat Datang di Perpustakaan")
                .setContentText("Jelajahi dunia sihir Harry Potter!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }
}
