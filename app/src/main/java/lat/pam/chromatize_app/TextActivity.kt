package lat.pam.chromatize_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TextActivity : AppCompatActivity() {

    private lateinit var selectedColorText: TextView
    private lateinit var descriptionEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text) // Menggunakan layout text.xml

        selectedColorText = findViewById(R.id.selected_color_text)
        descriptionEditText = findViewById(R.id.description_edittext)

        // Set up color buttons
        setupColorButton(R.id.blue_button, "Blue")
        setupColorButton(R.id.green_button, "Green")
        setupColorButton(R.id.yellow_button, "Yellow")
        setupColorButton(R.id.orange_button, "Orange")
        setupColorButton(R.id.red_button, "Red")

        // Setup Cancel button
        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            descriptionEditText.text.clear()
            selectedColorText.text = "Selected Color: None"
        }

        // Setup Save button
        findViewById<Button>(R.id.save_button).setOnClickListener {
            val description = descriptionEditText.text.toString()
            val selectedColor = selectedColorText.text.toString()

            // Cek apakah warna dipilih dan pesan diketik
            if (selectedColor == "Selected Color: None" || description.isEmpty()) {
                Toast.makeText(this, "Please select a color or type your experience.", Toast.LENGTH_SHORT).show()
            } else {
                // Tampilkan pesan "Tulisan tersimpan"
                Toast.makeText(this, "The added successfully", Toast.LENGTH_SHORT).show()

                // Kembali ke MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Mengakhiri TextActivity jika tidak ingin kembali ke halaman ini
            }
        }
    }

    private fun setupColorButton(buttonId: Int, color: String) {
        findViewById<TextView>(buttonId).setOnClickListener {
            selectedColorText.text = "Selected Color: $color"
        }
    }
}