package uz.bigboys.hellocustomview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.bigboys.hellocustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      val binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      binding.view1.setOnClickListener {
         binding.view1.setImageVisible(!binding.view1.imageVisibility)
      }
      binding.view2.setOnClickListener {
         binding.view2.setImageVisible(!binding.view2.imageVisibility)
      }
   }
}