package lat.pam.chromatize_app

data class HistoryItem(
    val source: String, // Sumber aktivitas (misal: "ColorPicker", "Camera", dll.)
    val description: String, // Deskripsi item
    val interpretation: String?
)