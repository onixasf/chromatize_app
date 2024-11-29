package lat.pam.chromatize_app

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ColorPickerActivity : AppCompatActivity() {
    private lateinit var circularColorPickerView: CircularColorPickerView
    private lateinit var confirmButton: ImageView
    private lateinit var colorInterpretationTextView: TextView // TextView for color interpretation

    private var selectedColor: Int = Color.RED // Default color

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_picker)

        circularColorPickerView = findViewById(R.id.color_flower_view)
        confirmButton = findViewById(R.id.confirm_button)
        colorInterpretationTextView = findViewById(R.id.colorInterpretationTextView)

        // Set listener for color change
        circularColorPickerView.setColorChangeListener { color ->
            selectedColor = color
            updateColorInterpretation(selectedColor) // Update color interpretation
        }

        // Set listener for confirm button
        confirmButton.setOnClickListener {
            val colorName = ColorHelper.getColorName(selectedColor)
            Toast.makeText(this, "Confirmed color: $colorName", Toast.LENGTH_SHORT).show()

            // You can remove this part if history-saving is no longer needed
            // saveSelectedColorToHistory(colorName)

            finish() // Close activity after confirmation
        }
    }

    private fun updateColorInterpretation(color: Int) {
        val interpretation = when (color) {
            Color.RED -> "Merah: I feel so sad"
            Color.parseColor("#FFA500") -> "Oranye: I feel sad"
            Color.YELLOW -> "Kuning: I don't feel anything at all"
            Color.parseColor("#FF008000") -> "Hijau: I feel great"
            Color.BLUE -> "Biru: I feel so happy"
            else -> "Warna tidak dikenali"
        }
        colorInterpretationTextView.text = interpretation // Update TextView with interpretation
    }

    // This function has been removed
    // private fun saveSelectedColorToHistory(colorName: String) {
    //     val interpretation = colorInterpretationTextView.text.toString()
    //     ColorHistory.history.add(HistoryItem("ColorPicker", "Selected Color: $colorName", interpretation))
    // }
}