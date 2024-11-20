package lat.pam.chromatize_app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PlaylistAdapter(private val context: Context, private val playlists: List<PlaylistItem>) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    inner class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.playlist_name)
        val imageView: ImageView = itemView.findViewById(R.id.playlist_image)

        fun bind(playlist: PlaylistItem) {
            textView.text = playlist.name
            imageView.setImageResource(playlist.imageResId)

            itemView.setOnClickListener {
                // Gunakan spotifyUrl untuk membuka playlist
                val uri = Uri.parse(playlist.spotifyUrl)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.playlist, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(playlists[position])
    }

    override fun getItemCount(): Int {
        return playlists.size
    }
}