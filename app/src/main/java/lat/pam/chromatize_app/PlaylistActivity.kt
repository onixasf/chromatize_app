package lat.pam.chromatize_app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlaylistActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var playlistAdapter: PlaylistAdapter

    // Daftar playlist
    private val playlists = listOf(
        PlaylistItem("37i9dQZF1EP6YuccBxUcC1", "Chill", imageResId = R.drawable.summer, spotifyUrl = "https://open.spotify.com/playlist/63aOMpGXakIizfhsXu6p9E?si=beb8f3cca1e0492b"),
        PlaylistItem("37i9dQZF1E8GZ7m7YlD5x9", "Rock", imageResId = R.drawable.rock, spotifyUrl = "https://open.spotify.com/playlist/37i9dQZF1DXcF6B6QPhFDv"),
        PlaylistItem("7l17n1355cPt14myva89We", "Moodbooster", imageResId = R.drawable.happyplaylist, spotifyUrl = "https://open.spotify.com/playlist/7l17n1355cPt14myva89We?si=de2725c600dd44d0"),
        PlaylistItem("6yYA6aUGp8qUTgQWWYkPkP", "If you need to cry", imageResId = R.drawable.sadplaylist, spotifyUrl = "https://open.spotify.com/playlist/6yYA6aUGp8qUTgQWWYkPkP?si=ba144a1599fb43b2"),
    )

    // Mapping tombol warna ke mood
    private val moodPlaylistMap = mapOf(
        R.id.blue_button to { showPlaylists(listOf("Chill", "Moodbooster")) },
        R.id.green_button to { showPlaylists(listOf("Moodbooster")) },
        R.id.yellow_button to { showPlaylists(listOf("Rock")) },
        R.id.orange_button to { showPlaylists(listOf("If you need to cry")) },
        R.id.red_button to { showAllPlaylists() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_playlist)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi adapter dengan daftar playlist
        playlistAdapter = PlaylistAdapter(this, playlists)
        recyclerView.adapter = playlistAdapter

        // Setup color buttons
        setupColorButtons()
    }

    private fun setupColorButtons() {
        moodPlaylistMap.keys.forEach { buttonId ->
            findViewById<View>(buttonId).setOnClickListener {
                moodPlaylistMap[buttonId]?.invoke()
            }
        }
    }

    private fun showPlaylists(moodPlaylists: List<String>) {
        val filteredPlaylists = playlists.filter { moodPlaylists.contains(it.name) }
        playlistAdapter.updatePlaylists(filteredPlaylists)
    }

    private fun showAllPlaylists() {
        playlistAdapter.updatePlaylists(playlists)
    }
}