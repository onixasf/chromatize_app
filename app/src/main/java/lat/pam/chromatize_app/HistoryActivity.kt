package lat.pam.chromatize_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class HistoryActivity : AppCompatActivity() {
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter

    private val moodData = listOf(
        MoodData("Happy", Mood.HAPPY.value),
        MoodData("Good", Mood.GOOD.value),
        MoodData("Neutral", Mood.NEUTRAL.value),
        MoodData("Sad", Mood.SAD.value),
        MoodData("Very Bad", Mood.VERY_BAD.value)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // Inisialisasi RecyclerView
        historyRecyclerView = findViewById(R.id.historyRecyclerView)
        historyRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize historyAdapter with the data
        historyAdapter = HistoryAdapter(moodData)

        // Set up GraphView
        setupGraph()

        // Set the adapter to the RecyclerView
        historyRecyclerView.adapter = historyAdapter
    }

    // Fungsi untuk menyiapkan GraphView dengan data mood
    private fun setupGraph() {
        val graph: GraphView = findViewById(R.id.graphEmot)
        val series = LineGraphSeries<DataPoint>()

        // Menambahkan data mood ke grafik
        for (i in moodData.indices) {
            val xValue = i.toDouble()  // Sumbu X (misalnya hari atau urutan)
            val yValue = moodData[i].value.toDouble()  // Sumbu Y (nilai mood)
            series.appendData(DataPoint(xValue, yValue), true, moodData.size)
        }

        graph.addSeries(series)
        graph.gridLabelRenderer.isHorizontalLabelsVisible = false
        graph.gridLabelRenderer.isVerticalLabelsVisible = false
    }
}

// Data kelas untuk menyimpan informasi mood
data class MoodData(val name: String, val value: Int)

// Enum untuk pemetaan nilai mood
enum class Mood(val value: Int) {
    HAPPY(1),
    GOOD(2),
    NEUTRAL(3),
    SAD(4),
    VERY_BAD(5)
}