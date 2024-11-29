package lat.pam.chromatize_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter(
    private val articles: List<ArticleRecommendation>,
    private val onItemClick: (String) -> Unit // Callback untuk menangani klik
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: ArticleRecommendation) {
            itemView.findViewById<TextView>(R.id.article_title).text = article.title
            itemView.findViewById<TextView>(R.id.article_description).text = article.description
            itemView.findViewById<ImageView>(R.id.article_image).setImageResource(article.imageRes)

            // Menangani klik pada item
            itemView.setOnClickListener {
                Log.d("ArticleAdapter", "Item clicked: ${article.url}") // Logging untuk debugging
                onItemClick(article.url) // Panggil callback dengan URL
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size
}