import android.app.PendingIntent
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import com.example.composepraktik.MainActivity
import com.example.composepraktik.MyService
import com.example.composepraktik.NotificationHelper

@Composable
fun NotificationScreen(notificationHelper: NotificationHelper) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Button(onClick = {
            val builder = notificationHelper
                .builder(NotificationHelper.CHANNEL_SIMPLE)
                .setContentTitle("Простое уведомление")
                .setContentText("Это пример стандартного уведомления")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            notificationHelper.manager.notify(1, builder.build())
        }) {
            Text("1. Простое уведомление")
        }

        Button(onClick = {
            val intent = Intent(notificationHelper.context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                notificationHelper.context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val builder = notificationHelper
                .builder(NotificationHelper.CHANNEL_APP)
                .setContentTitle("Открыть приложение")
                .setContentText("Нажми, чтобы открыть приложение")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            notificationHelper.manager.notify(2, builder.build())
        }) {
            Text("2. Уведомление, открывающее приложение")
        }

        Button(onClick = {
            val intent = Intent(notificationHelper.context, MyService::class.java).apply {
                putExtra("command", "DO_WORK")
            }

            val pendingIntent = PendingIntent.getService(
                notificationHelper.context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val builder = notificationHelper
                .builder(NotificationHelper.CHANNEL_SERVICE)
                .setContentTitle("Команда сервису")
                .setContentText("Нажми, чтобы отправить команду")
                .addAction(0, "Выполнить", pendingIntent)

            notificationHelper.manager.notify(3, builder.build())
        }) {
            Text("3. Уведомление с командой сервису")
        }

        Button(onClick = {
            val builderHigh = notificationHelper
                .builder(NotificationHelper.CHANNEL_APP)
                .setContentTitle("Высокий приоритет")
                .setContentText("Это уведомление приходит по каналу с вибрацией")
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            notificationHelper.manager.notify(4, builderHigh.build())
        }) {
            Text("4. Уведомление через канал с высоким приоритетом")
        }

        Button(onClick = {
            val builder = notificationHelper
                .builder(NotificationHelper.CHANNEL_SIMPLE)
                .setContentTitle("Экран блокировки")
                .setContentText("Это уведомление видно на заблокированном экране")
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

            notificationHelper.manager.notify(5, builder.build())
        }) {
            Text("5. Уведомление на экран блокировки")
        }
    }
}
