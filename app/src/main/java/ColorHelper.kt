object ColorHelper {
    private val colorList = listOf(
        0xFFFF0000.toInt(), // Merah
        0xFFFFA500.toInt(), // Oranye
        0xFFFFFF00.toInt(), // Kuning
        0xFF008000.toInt(), // Hijau
        0xFF0000FF.toInt(), // Biru
        0xFF800080.toInt(), // Ungu
    )

    fun getColorList(): List<Int> {
        return colorList
    }

    fun getColorName(color: Int): String {
        return when (color) {
            0xFFFF0000.toInt() -> "Merah"
            0xFFFFA500.toInt() -> "Oranye"
            0xFFFFFF00.toInt() -> "Kuning"
            0xFF008000.toInt() -> "Hijau"
            0xFF0000FF.toInt() -> "Biru"
            0xFF800080.toInt() -> "Ungu"
            else -> "Tidak Dikenal"
        }
    }
}