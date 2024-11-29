package lat.pam.chromatize_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewArticleRecommendations: RecyclerView
    private lateinit var adapter: ArticleRecommendationAdapter

    private lateinit var recyclerViewMeditationRecommendations: RecyclerView
    private lateinit var meditationAdapter: MeditationRecommendationAdapter

    private lateinit var fabMain: FloatingActionButton
    private lateinit var subButtonsLayout: LinearLayout
    private lateinit var fabMusic: FloatingActionButton
    private lateinit var fabCamera: FloatingActionButton
    private lateinit var fabText: FloatingActionButton
    private lateinit var fabColor: FloatingActionButton
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private var isSubButtonsVisible = false

    // Rekomendasi Artikel
    private val articleRecommendations = listOf(
        ArticleRecommendation(
            "Bagaimana Cara...",
            "https://www.psychologytoday.com/intl/basics/happiness/how-to-find-happiness",
            "Jelajahi pilar kebahagiaan, termasuk perhatian, hubungan, dan banyak lagi.",
            R.drawable.find_happiness
        ),
        ArticleRecommendation(
            "Apa Itu Kesehatan...",
            "https://www.siloamhospitals.com/informasi-siloam/artikel/apa-itu-mental-health",
            "Pelajari tentang pentingnya kesehatan mental dan cara menjaganya.",
            R.drawable.kesehatan_mental
        ),
        ArticleRecommendation(
            "Cara Mengatasi...",
            "https://www.alodokter.com/ketahui-cara-mengatasi-gangguan-kecemasan",
            "Temukan teknik efektif untuk mengelola dan mengatasi kecemasan.",
            R.drawable.gangguan_kecemasan
        ),
        ArticleRecommendation(
            "Mengatasi Stress...",
            "https://www.alodokter.com/ternyata-tidak-sulit-mengatasi-stres",
            "Jelajahi cara mudah dan efektif untuk mengatasi stres dalam hidup Anda.",
            R.drawable.mengatasi_stress
        ),
        ArticleRecommendation(
            "Cara Meditasi...",
            "https://www.aia-financial.co.id/id/health-and-wellness/aia-content-club/mental-wellness/Ini-Cara-Meditasi-untuk-Menenangkan-Pikiran",
            "Berikut cara melakukan meditasi dengan mudah",
            R.drawable.meditasi
        )
    )

    // Rekomendasi Meditasi
    private val meditationRecommendations = listOf(
        MeditationRecommendation(
            "Stress Relief Playlist",
            "Dengarkan playlist ini untuk meredakan stres.",
            R.drawable.summer, // Ganti dengan nama gambar yang sesuai
            "https://open.spotify.com/playlist/37i9dQZF1DWXe9gFZP0gtP?si=8b0ef56b0c1f4702"
        ),
        MeditationRecommendation(
            "Relaxing Music Playlist",
            "Nikmati musik menenangkan untuk belajar, relaksasi, atau tidur.",
            R.drawable.summer, // Ganti dengan nama gambar yang sesuai
            "https://open.spotify.com/playlist/7AZuDiVAVNfBQCI1cnUfMX?si=i-r2wtF_RDGW_vlWWhZClQ"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        historyRecyclerView = findViewById(R.id.rvCategories)
        historyRecyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the adapter with the color history
        historyAdapter = HistoryAdapter(ColorHistory.history)
        historyRecyclerView.adapter = historyAdapter

        // Mencari ImageView untuk Rekomendasi Artikel
        val historyImage = findViewById<ImageView>(R.id.historyImageView)
        historyImage.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        // Mencari ImageView untuk Rekomendasi Artikel
        val articleRecommendationsImage = findViewById<ImageView>(R.id.articleRecommendationsImageView)
        articleRecommendationsImage.setOnClickListener {
            val intent = Intent(this, ArticleActivity::class.java)
            startActivity(intent)
        }

        // Mencari ImageView untuk Rekomendasi Artikel
        val meditationPlaylistImage = findViewById<ImageView>(R.id.meditationRecommendationsImageView)
        meditationPlaylistImage.setOnClickListener {
            val intent = Intent(this, MeditationPlaylistActivity::class.java)
            startActivity(intent)
        }

        // Inisialisasi RecyclerView untuk artikel rekomendasi
        recyclerViewArticleRecommendations = findViewById(R.id.recyclerViewArticleRecommendations)
        recyclerViewArticleRecommendations.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = ArticleRecommendationAdapter(articleRecommendations)
        recyclerViewArticleRecommendations.adapter = adapter

        // Inisialisasi RecyclerView untuk meditasi rekomendasi
        recyclerViewMeditationRecommendations = findViewById(R.id.recyclerViewMeditationRecommendations)
        recyclerViewMeditationRecommendations.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        meditationAdapter = MeditationRecommendationAdapter(meditationRecommendations)
        recyclerViewMeditationRecommendations.adapter = meditationAdapter

        initViews()
        setListeners()
    }

    private fun initViews() {
        fabMain = findViewById(R.id.fab)
        subButtonsLayout = findViewById(R.id.sub_buttons_layout)
        fabMusic = findViewById(R.id.button_music)
        fabCamera = findViewById(R.id.button_camera)
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
            val intent = Intent(this, PlaylistActivity::class.java)
            startActivity(intent)
            hideSubButtons()
            fabMain.setImageResource(R.drawable.ic_add)
        }

        fabCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
            hideSubButtons()
            fabMain.setImageResource(R.drawable.ic_add)
        }

        fabText.setOnClickListener {
            val intent = Intent(this, TextActivity::class.java)
            startActivity(intent)
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

class ArticleRecommendationAdapter(
    private val articleRecommendations: List<ArticleRecommendation>
) : RecyclerView.Adapter<ArticleRecommendationAdapter.ArticleRecommendationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleRecommendationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article_recommendations, parent, false)
        return ArticleRecommendationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleRecommendationViewHolder, position: Int) {
        val articleRecommendation = articleRecommendations[position]
        holder.bind(articleRecommendation)
    }

    override fun getItemCount(): Int {
        return articleRecommendations.size
    }

    inner class ArticleRecommendationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewArticle: ImageView = itemView.findViewById(R.id.imageViewArticle)
        private val textViewArticleTitle: TextView = itemView.findViewById(R.id.textViewArticleTitle)
        private val textViewArticleDescription: TextView = itemView.findViewById(R.id.textViewArticleDescription)

        fun bind(articleRecommendation: ArticleRecommendation) {
            imageViewArticle.setImageResource(articleRecommendation.imageRes) // Memuat gambar dari resource ID
            textViewArticleTitle.text = articleRecommendation.title
            textViewArticleDescription.text = articleRecommendation.description

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(articleRecommendation.url))
                itemView.context.startActivity(intent)
            }
        }
    }
}

class MeditationRecommendationAdapter(
    private val meditationRecommendations: List<MeditationRecommendation>
) : RecyclerView.Adapter<MeditationRecommendationAdapter.MeditationRecommendationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationRecommendationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meditation_recommendations, parent, false)
        return MeditationRecommendationViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeditationRecommendationViewHolder, position: Int) {
        val meditationRecommendation = meditationRecommendations[position]
        holder.bind(meditationRecommendation)
    }

    override fun getItemCount(): Int {
        return meditationRecommendations.size
    }

    inner class MeditationRecommendationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewMeditation: ImageView = itemView.findViewById(R.id.imageViewMeditation)
        private val textViewMeditationTitle: TextView = itemView.findViewById(R.id.textViewMeditationTitle)
        private val textViewMeditationDescription: TextView = itemView.findViewById(R.id.textViewMeditationDescription)

        fun bind(meditationRecommendation: MeditationRecommendation) {
            imageViewMeditation.setImageResource(meditationRecommendation.imageRes)
            textViewMeditationTitle.text = meditationRecommendation.title
            textViewMeditationDescription.text = meditationRecommendation.description

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(meditationRecommendation.url))
                itemView.context.startActivity(intent)
            }
        }
    }
}

data class ArticleRecommendation(
    val title: String,
    val url: String,
    val description: String,
    val imageRes: Int
)

data class MeditationRecommendation(
    val title: String,
    val description: String,
    val imageRes: Int,
    val url: String
)