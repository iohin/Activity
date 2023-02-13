package otus.gpb.homework.activities.sender

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class SenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender)

        findViewById<Button>(R.id.activity_sender_to_gmaps).setOnClickListener {
            val gmmIntentUri =
                Uri.parse("geo:0,0?q=Restaurants")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            try {
                startActivity(mapIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, R.string.google_maps_not_installed, Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.activity_sender_send_email).setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:android@otus.ru")).apply {
                    putExtra(Intent.EXTRA_SUBJECT, "Subject")
                    putExtra(Intent.EXTRA_TEXT, "Body")
                })
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, R.string.email_clients_not_installed, Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.activity_sender_open_receiver).setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_SEND).apply {
                    setDataAndType(Uri.parse("some data"), "text/plain")
                    addCategory( Intent.CATEGORY_DEFAULT)
                    putExtra("title", "Славные парни")
                    putExtra("year", "2016")
                    putExtra("description", "Что бывает, когда напарником брутального костолома становится субтильный лопух? Наемный охранник Джексон Хили и частный детектив Холланд Марч вынуждены работать в паре, чтобы распутать плевое дело о пропавшей девушке, которое оборачивается преступлением века. Смогут ли парни разгадать сложный ребус, если у каждого из них – свои, весьма индивидуальные методы.")
                })
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}