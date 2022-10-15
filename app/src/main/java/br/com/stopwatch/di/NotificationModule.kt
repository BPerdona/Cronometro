package br.com.stopwatch.di

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import br.com.stopwatch.R
import br.com.stopwatch.service.ServiceHelper
import br.com.stopwatch.util.Constants.NOTIFICATION_CHANNEL_ID
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@InstallIn(ServiceComponent::class)
object NotificationModule {


    @ServiceScoped
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder{
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Cronômetro")
            .setContentText("00:00:00")
            .setSmallIcon(R.drawable.timer_svgrepo_com)
            .setAutoCancel(false)
            .setOngoing(true)
            .addAction(0,"Parar", ServiceHelper.clickPendingIntent(context))
            .addAction(0,"Cancelar", ServiceHelper.cancelPendingIntent(context))
            .setContentIntent(ServiceHelper.cancelPendingIntent(context))
    }

    @ServiceScoped
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManager{
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
}