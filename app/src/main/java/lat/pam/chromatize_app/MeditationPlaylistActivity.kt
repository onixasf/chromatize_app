package lat.pam.chromatize_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MeditationPlaylistActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var meditationAdapter: MeditationAdapter

    // Contoh data meditasi
    private val meditations = listOf(
        MeditationRecommendation(
            "Stress Relief Playlist",
            "Dengarkan playlist ini untuk meredakan stres.",
            R.drawable.summer,
            "https://open.spotify.com/playlist/37i9dQZF1DWXe9gFZP0gtP?si=8b0ef56b0c1f4702"
        ),
        MeditationRecommendation(
            "Relaxing Music Playlist",
            "Nikmati musik menenangkan untuk belajar, relaksasi, atau tidur.",
            R.drawable.summer,
            "https://open.spotify.com/playlist/7AZuDiVAVNfBQCI1cnUfMX?si=i-r2wtF_RDGW_vlWWhZClQ"
        )
        // Tambahkan meditasi lain sesuai kebutuhan
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditation_playlist)

        recyclerView = findViewById(R.id.recyclerViewMeditationPlaylist)
        recyclerView.layoutManager = LinearLayoutManager(this)

        meditationAdapter = MeditationAdapter(meditations) { meditation ->
            // Tangani klik pada item meditasi
            // Misalnya, membuka URL di browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(meditation.url))
            startActivity(intent)
        }

        recyclerView.adapter = meditationAdapter
    }
}