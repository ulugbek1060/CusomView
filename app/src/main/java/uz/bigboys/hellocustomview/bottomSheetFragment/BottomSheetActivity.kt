package uz.bigboys.hellocustomview.bottomSheetFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import uz.bigboys.hellocustomview.R
import uz.bigboys.hellocustomview.databinding.ActivityBottomSheetBinding


class BottomSheetActivity : AppCompatActivity() {

   private lateinit var binding: ActivityBottomSheetBinding
   private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

   @SuppressLint("CommitTransaction")
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityBottomSheetBinding.inflate(layoutInflater)
      setContentView(binding.root)


      val bottomSheetFragment = BottomSheetFragment()
      supportFragmentManager.beginTransaction()
         .replace(R.id.containerBottomSheet, bottomSheetFragment)
         .commit()

      bottomSheetBehavior = BottomSheetBehavior.from(binding.containerBottomSheet)

      bottomSheetBehavior.addBottomSheetCallback(object :
         BottomSheetBehavior.BottomSheetCallback() {
         override fun onStateChanged(bottomSheet: View, newState: Int) = Unit
         override fun onSlide(bottomSheet: View, slideOffset: Float) {
            if (slideOffset >= 0) {
               bottomSheetFragment.setOpenProgress(slideOffset)
            }
         }
      })

      binding.btnClose.setOnClickListener {
         bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
      }
      binding.btnCloseToPeek.setOnClickListener {
         bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
      }
      binding.btnOpenHalf.setOnClickListener {
         bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
      }
      binding.btnOpen.setOnClickListener {
         bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
      }
   }
}