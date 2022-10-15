package br.com.stopwatch.di

import android.app.NotificationManager
import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.app.NotificationCompat
import br.com.stopwatch.R
import br.com.stopwatch.service.ServiceHelper
import br.com.stopwatch.util.Constants.NOTIFICATION_CHANNEL_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@ExperimentalAnimationApi
@Module
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
            .setOngoing(true)
            .addAction(0,"Parar", ServiceHelper.clickPendingIntent(context))
            .addAction(0,"Zerar", ServiceHelper.cancelPendingIntent(context))
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