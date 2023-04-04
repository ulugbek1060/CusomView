package uz.bigboys.hellocustomview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class TouchEventView @JvmOverloads constructor(
   context: Context,
   attrs: AttributeSet
) : View(context, attrs) {

   private val TAG = "TouchEventView"

   private val paint = Paint()
   private val path = Path()
   private var gestureDetector: GestureDetector


   init {
      gestureDetector = GestureDetector(context, GestureDetectorListener())
      paint.isAntiAlias = true
      paint.strokeWidth = 6f
      paint.color = Color.BLACK
      paint.style = Paint.Style.STROKE
      paint.strokeJoin = Paint.Join.ROUND
   }


   fun setColor(r: Int, g: Int, b: Int) {
      val rgb = Color.rgb(r, g, b)
      paint.color = rgb
   }

   override fun onDraw(canvas: Canvas) {
      canvas.drawPath(path, paint)
   }

   @SuppressLint("ClickableViewAccessibility")
   override fun onTouchEvent(event: MotionEvent): Boolean {
      val eventX = event.x
      val eventY = event.y
      when (event.action) {
         MotionEvent.ACTION_DOWN -> path.moveTo(eventX, eventY)
         MotionEvent.ACTION_MOVE -> path.lineTo(eventX, eventY)
         MotionEvent.ACTION_UP -> {}
         else -> return false
      }


      // for demonstration purposes
      gestureDetector.onTouchEvent(event)
      invalidate()
      return true
   }


   inner class GestureDetectorListener : GestureDetector.SimpleOnGestureListener() {
      override fun onDoubleTap(e: MotionEvent): Boolean {
         val x = e.x
         val y = e.y
         path.reset() // resets painted screen
         Log.d(TAG, "Tapped at: ($x,$y)")
         return true
      }


   }
}