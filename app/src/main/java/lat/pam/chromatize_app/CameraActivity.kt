package lat.pam.chromatize_app

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CameraActivity : AppCompatActivity() {

    private lateinit var cameraIcon: ImageView
    private lateinit var galleryIcon: ImageView
    private lateinit var imageView: ImageView
    private lateinit var btnCancel: Button
    private lateinit var btnSave: Button
    private lateinit var selectedColorText: TextView
    private lateinit var colorInfoTextView: TextView
    private var imageBitmap: Bitmap? = null
    private var selectedMoodColor: String? = null

    private val colors = mapOf(
        R.id.blue_button to Pair("Blue", 0xFF0099CC.toInt()),
        R.id.green_button to Pair("Green", 0xFF99CC00.toInt()),
        R.id.yellow_button to Pair("Yellow", 0xFFFFC107.toInt()),
        R.id.orange_button to Pair("Orange", 0xFFFF8800.toInt()),
        R.id.red_button to Pair("Red", 0xFFCC0000.toInt())
    )

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageBitmap = result.data?.extras?.get("data") as? Bitmap
            if (imageBitmap != null) {
                imageView.setImageBitmap(imageBitmap)
                identifyColors(imageBitmap!!)
            } else {
                Toast.makeText(this, "Gagal menangkap gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            if (imageUri != null) {
                imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                imageView.setImageBitmap(imageBitmap)
                identifyColors(imageBitmap!!)
            } else {
                Toast.makeText(this, "Gagal mengambil gambar dari galeri", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera)

        // Inisialisasi elemen UI
        cameraIcon = findViewById(R.id.cameraIcon)
        galleryIcon = findViewById(R.id.galleryIcon)
        imageView = findViewById(R.id.imageView)
        btnCancel = findViewById(R.id.cancelButton)
        btnSave = findViewById(R.id.saveButton)
        selectedColorText = findViewById(R.id.selected_color_text)
        colorInfoTextView = findViewById(R.id.colorInfoTextView)

        /// Set up color buttons
        colors.forEach { (buttonId, colorPair) ->
            setupColorButton(buttonId, colorPair.first, colorPair.second)
        }

        // Setup Cancel button
        btnCancel.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        requestPermissions()  // Meminta izin saat Activity dimulai

        cameraIcon.setOnClickListener {
            openCamera()
        }

        galleryIcon.setOnClickListener {
            openGallery()
        }

        btnSave.setOnClickListener {
            if (imageBitmap != null) {
                if (selectedMoodColor != null) {
                    // Mood color is selected, save mood and colors
                    saveImage(imageBitmap!!) // Save the captured image

                    // Navigate back to MainActivity
                    startActivity(Intent(this, MainActivity::class.java))
                    finish() // Close CameraActivity
                } else {
                    // Show a message if no mood color is selected
                    Toast.makeText(this, "Silakan pilih mood color sebelum menyimpan", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tidak ada gambar untuk disimpan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupColorButton(buttonId: Int, colorName: String, colorValue: Int) {
        findViewById<TextView>(buttonId).apply {
            setOnClickListener {
                selectedColorText.text = "Selected Color: $colorName"
                selectedMoodColor = colorName // Save the selected mood color
            }
        }
    }

    private fun identifyColors(bitmap: Bitmap) {
        val dominantColors = getDominantColors(bitmap)
        val colorInfo = StringBuilder()

        for (color in dominantColors) {
            val colorHex = String.format("#%06X", (0xFFFFFF and color)) // Mengubah warna ke format heksadesimal
            colorInfo.append("Color: $colorHex\n")
        }

        // Update the color info text view
        colorInfoTextView.text = colorInfo.toString()
    }

    private fun getDominantColors(bitmap: Bitmap): IntArray {
        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        val colorCount = HashMap<Int, Int>()
        for (pixel in pixels) {
            val color = pixel and 0xFFFFFF
            colorCount[color] = (colorCount[color] ?: 0) + 1
        }
        return colorCount.entries.sortedByDescending { it.value }.take(3).map { it.key }.toIntArray()
    }

    private fun saveImage(bitmap: Bitmap) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "image_with_colors_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            try {
                contentResolver.openOutputStream(it).use { outputStream ->
                    if (outputStream != null) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        outputStream.flush()
                        Toast.makeText(this, "Gambar berhasil disimpan", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Gagal mendapatkan aliran untuk menyimpan gambar", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error saat menyimpan gambar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(this, "Gagal mendapatkan URI untuk menyimpan gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestPermissions() {
        val permissionsNeeded = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsNeeded.toTypedArray(), 100)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // Izin diberikan, Anda bisa membuka kamera atau galeri
            } else {
                Toast.makeText(this, "Izin diperlukan untuk menggunakan kamera dan galeri", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(intent)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }
}