package lat.pam.chromatize_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val historyItems: List<MoodData>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sourceTextView: TextView = itemView.findViewById(R.id.sourceTextView)
        val colorTextView: TextView = itemView.findViewById(R.id.colorTextView)
        val interpretationTextView: TextView = itemView.findViewById(R.id.interpretationTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyItem = historyItems[position]
        holder.sourceTextView.text = historyItem.name  // Set mood name to sourceTextView
        holder.colorTextView.text = historyItem.value.toString()  // Set mood value to colorTextView
        holder.interpretationTextView.text = "Interpretation: Not available"  // Or handle as needed
    }

    override fun getItemCount(): Int = historyItems.size
}