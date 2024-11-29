package lat.pam.chromatize_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticleActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter

    private val articles = listOf(
        ArticleRecommendation(
            "Bagaimana Cara Mencari Kebahagiaan",
            "https://www.psychologytoday.com/intl/basics/happiness/how-to-find-happiness",
            "Jelajahi pilar kebahagiaan, termasuk perhatian, hubungan, dan banyak lagi.",
            R.drawable.find_happiness
        ),
        ArticleRecommendation(
            "Apa Itu Kesehatan Mental?",
            "https://www.siloamhospitals.com/informasi-siloam/artikel/apa-itu-mental-health",
            "Pelajari tentang pentingnya kesehatan mental dan cara menjaganya.",
            R.drawable.kesehatan_mental
        ),
        ArticleRecommendation(
            "Bagaimana Mengatasi Gangguan Kecemasan dengan Benar?",
            "https://www.alodokter.com/ketahui-cara-mengatasi-gangguan-kecemasan",
            "Temukan teknik efektif untuk mengelola dan mengatasi kecemasan.",
            R.drawable.gangguan_kecemasan
        ),
        ArticleRecommendation(
            "Berikut Cara Mengatasi Stres dengan Mudah",
            "https://www.alodokter.com/ternyata-tidak-sulit-mengatasi-stres",
            "Jelajahi cara mudah dan efektif untuk mengatasi stres dalam hidup Anda.",
            R.drawable.mengatasi_stress
        ),
        ArticleRecommendation(
            "Melakukan Meditasi untuk Menenangkan Pikiran",
            "https://www.aia-financial.co.id/id/health-and-wellness/aia-content-club/mental-wellness/Ini-Cara-Meditasi-untuk-Menenangkan-Pikiran",
            "Berikut cara melakukan meditasi dengan mudah.",
            R.drawable.meditasi
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_recommendations)

        recyclerView = findViewById(R.id.recyclerViewArticles)
        recyclerView.layoutManager = LinearLayoutManager(this)

        articleAdapter = ArticleAdapter(articles) { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            Log.d("ArticleActivity", "Opening URL: $url") // Logging untuk debugging
            startActivity(intent)
        }

        recyclerView.adapter = articleAdapter
    }
}