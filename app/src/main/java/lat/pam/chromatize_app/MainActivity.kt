package lat.pam.chromatize_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var fabMain: FloatingActionButton
    private lateinit var subButtonsLayout: LinearLayout
    private lateinit var fabMusic: FloatingActionButton
    private lateinit var fabText: FloatingActionButton
    private lateinit var fabColor: FloatingActionButton

    private var isSubButtonsVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setListeners()
    }

    private fun initViews() {
        fabMain = findViewById(R.id.fab)
        subButtonsLayout = findViewById(R.id.sub_buttons_layout)
        fabMusic = findViewById(R.id.button_music)
        fabText = findViewById(R.id.button_text)
        fabColor = findViewById(R.id.button_color)
    }

    private fun setListeners() {
        fabMain.setOnClickListener {
            if (!isSubButtonsVisible) {
                showSubButtons()
                fabMain.setImageResource(R.drawable.ic_close)
            } else {
                hideSubButtons()
                fabMain.setImageResource(R.drawable.ic_add)
            }
        }

        fabMusic.setOnClickListener {
            // Lakukan tindakan untuk tombol musik
            hideSubButtons()
            fabMain.setImageResource(R.drawable.ic_add)
        }

        fabText.setOnClickListener {
            // Lakukan tindakan untuk tombol teks
            hideSubButtons()
            fabMain.setImageResource(R.drawable.ic_add)
        }

        fabColor.setOnClickListener {
            val intent = Intent(this, ColorPickerActivity::class.java)
            startActivity(intent)
            hideSubButtons()
            fabMain.setImageResource(R.drawable.ic_add)
        }
    }

    private fun showSubButtons() {
        subButtonsLayout.visibility = View.VISIBLE
        isSubButtonsVisible = true
    }

    private fun hideSubButtons() {
        subButtonsLayout.visibility = View.GONE
        isSubButtonsVisible = false
    }
}