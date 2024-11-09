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
    private lateinit var colorInterpretationTextView: TextView // Tambahkan TextView untuk interpretasi

    private var selectedColor: Int = Color.RED // Warna default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_picker)

        circularColorPickerView = findViewById(R.id.color_flower_view)
        confirmButton = findViewById(R.id.confirm_button)
        colorInterpretationTextView = findViewById(R.id.colorInterpretationTextView) // Hubungkan TextView

        // Set listener untuk perubahan warna
        circularColorPickerView.setColorChangeListener { color ->
            selectedColor = color
            updateColorInterpretation(selectedColor) // Memperbarui interpretasi warna
        }

        // Set listener untuk tombol konfirmasi
        confirmButton.setOnClickListener {
            Toast.makeText(this, "Confirmed color: ${ColorHelper.getColorName(selectedColor)}", Toast.LENGTH_SHORT).show()
            finish() // Menutup activity setelah konfirmasi
        }
    }

    private fun updateColorInterpretation(color: Int) {
        val interpretation = when (color) {
            Color.RED -> "Merah: Saya merasa sangat sedih"
            Color.parseColor("#FFA500") -> "Oranye: Saya merasa sedih"
            Color.YELLOW -> "Kuning: Saya merasa biasa saja"
            Color.parseColor("#FF008000") -> "Hijau: Perasaan saya sedang bagus"
            Color.BLUE -> "Biru: Saya merasa sangat senang"
            else -> "Warna tidak dikenali"
        }
        colorInterpretationTextView.text = interpretation // Perbarui TextView dengan interpretasi
    }
}