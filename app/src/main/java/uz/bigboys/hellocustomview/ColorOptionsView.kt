package uz.bigboys.hellocustomview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import uz.bigboys.hellocustomview.databinding.ViewColorOptionsBinding


@SuppressLint("Recycle", "CustomViewStyleable", "ResourceAsColor")
class ColorOptionsView @JvmOverloads constructor(
   context: Context,
   attrs: AttributeSet,
   defStyleAttr: Int = 0,
   defStyleRes: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

   private var binding: ViewColorOptionsBinding

   init {
      val inflater = LayoutInflater.from(context)

      val typedArray =
         context.obtainStyledAttributes(attrs, R.styleable.Options, defStyleAttr, defStyleRes)
      val titleText = typedArray.getString(R.styleable.Options_titleText)
      val valueColor =
         typedArray.getColor(R.styleable.Options_valueColor, android.R.color.holo_blue_light)

      typedArray.recycle()

      orientation = HORIZONTAL
      gravity = Gravity.CENTER_VERTICAL

      binding = ViewColorOptionsBinding.inflate(inflater, this)

      binding.textView.text = titleText
      binding.view.setBackgroundColor(valueColor)
      binding.imageView.isVisible = true
   }

   val imageVisibility: Boolean get() = binding.imageView.isVisible

   fun setValueColor(color: Int) {
      binding.view.setBackgroundColor(color)
   }

   fun setImageVisible(visible: Boolean) {
      binding.imageView.visibility = if (visible) VISIBLE else GONE
   }
}