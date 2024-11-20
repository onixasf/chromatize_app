package lat.pam.chromatize_app

data class PlaylistItem(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val imageResId: Int,
    val spotifyUrl: String
)