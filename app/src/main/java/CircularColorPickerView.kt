package lat.pam.chromatize_app

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CircularColorPickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var radius = 0f
    private var centerX = 0f
    private var centerY = 0f
    private var selectedColor = Color.RED
    private var colorChangeListener: ((Int) -> Unit)? = null

    init {
        paint.isAntiAlias = true
        paint.shader = SweepGradient(0f, 0f, createColors(), null)
    }

    private fun createColors(): IntArray {
        // Menggunakan ColorHelper untuk mendapatkan daftar warna
        return ColorHelper.getColorList().toIntArray()
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        radius = (width.coerceAtMost(height) / 2 * 0.8).toFloat()
        centerX = (width / 2).toFloat()
        centerY = (height / 2).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(centerX, centerY)
        canvas.drawCircle(0f, 0f, radius, paint)

        // Lingkaran kecil untuk warna terpilih
        paint.color = selectedColor
        paint.style = Paint.Style.FILL
        canvas.drawCircle(0f, 0f, radius * 0.1f, paint) // Lingkaran kecil di tengah
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val color = getColorAt(event.x - centerX, event.y - centerY)
                if (color != selectedColor) {
                    selectedColor = color
                    colorChangeListener?.invoke(selectedColor)
                    invalidate() // Memperbarui tampilan
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun getColorAt(x: Float, y: Float): Int {
        val dx = x
        val dy = y
        val angle = Math.toDegrees(Math.atan2(dy.toDouble(), dx.toDouble())).toFloat()
        val adjustedAngle = (angle + 360) % 360
        val index = (adjustedAngle / 360 * (createColors().size - 1)).toInt()
        return createColors()[index]
    }

    fun setColorChangeListener(listener: (Int) -> Unit) {
        colorChangeListener = listener
    }
}