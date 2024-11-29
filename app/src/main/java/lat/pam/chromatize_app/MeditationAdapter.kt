package lat.pam.chromatize_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MeditationAdapter(
    private val meditations: List<MeditationRecommendation>,
    private val onItemClick: (MeditationRecommendation) -> Unit
) : RecyclerView.Adapter<MeditationAdapter.MeditationViewHolder>() {

    inner class MeditationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(meditation: MeditationRecommendation) {
            itemView.findViewById<TextView>(R.id.meditation_title).text = meditation.title
            itemView.findViewById<TextView>(R.id.meditation_description).text = meditation.description
            itemView.findViewById<ImageView>(R.id.meditation_image).setImageResource(meditation.imageRes)

            itemView.setOnClickListener { onItemClick(meditation) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meditation_playlist, parent, false)
        return MeditationViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) {
        holder.bind(meditations[position])
    }

    override fun getItemCount(): Int = meditations.size
}