package lat.pam.chromatize_app

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lat.pam.chromatize_app.databinding.ItemColorBinding

class ColorPickerAdapter(
    private val context: Context,
    private val colorList: List<Int>,
    private val onColorSelected: (Int) -> Unit
) : RecyclerView.Adapter<ColorPickerAdapter.ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = colorList[position]
        holder.binding.colorView.setBackgroundColor(color) // Mengatur warna latar belakang dengan benar
        holder.itemView.setOnClickListener {
            onColorSelected(color)
        }
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    inner class ColorViewHolder(val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root)
}