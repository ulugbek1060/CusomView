package uz.bigboys.hellocustomview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View


class MultitouchView @JvmOverloads constructor(
   context: Context, attrs: AttributeSet
) : View(context, attrs) {

   private val SIZE = 60

   private var activePointers: SparseArray<PointF> = SparseArray()
   private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
   private val colors = intArrayOf(
      Color.BLUE,
      Color.GREEN,
      Color.MAGENTA,
      Color.BLACK,
      Color.CYAN,
      Color.GRAY,
      Color.RED,
      Color.DKGRAY,
      Color.LTGRAY,
      Color.YELLOW
   )

   private var textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

   init {
      paint.color = Color.BLUE
      paint.style = Paint.Style.FILL_AND_STROKE
      textPaint.textSize = 20f
   }

   @SuppressLint("ClickableViewAccessibility")
   override fun onTouchEvent(event: MotionEvent): Boolean {

      // get pointer index from the event object
      val pointerIndex = event.actionIndex

      // get pointer ID
      val pointerId = event.getPointerId(pointerIndex)

      // get masked (not specific to a pointer) action

      when (event.actionMasked) {
         MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
            val f = PointF()
            f.x = event.getX(pointerIndex)
            f.y = event.getY(pointerIndex)
            activePointers.put(pointerId, f)
         }
         MotionEvent.ACTION_MOVE -> {
            val size = event.pointerCount
            var i = 0
            while (i < size) {
               val point: PointF = activePointers.get(event.getPointerId(i))
               if (point != null) {
                  point.x = event.getX(i)
                  point.y = event.getY(i)
               }
               i++
            }
         }
         MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
            activePointers.remove(pointerId)
         }
      }

      invalidate()
      return true
   }

   override fun onDraw(canvas: Canvas) {
      super.onDraw(canvas)
      // draw all pointers
      val size: Int = activePointers.size()
      var i = 0
      while (i < size) {
         val point: PointF = activePointers.valueAt(i)
         if (point != null) paint.color = colors[i % 9]
         canvas.drawCircle(point.x, point.y, SIZE.toFloat(), paint)
         i++
      }
      canvas.drawText("Total pointers: " + activePointers.size(), 10f, 40f, textPaint)
   }

}