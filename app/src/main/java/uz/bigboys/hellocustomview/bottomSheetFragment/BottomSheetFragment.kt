package uz.bigboys.hellocustomview.bottomSheetFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import uz.bigboys.hellocustomview.R
import uz.bigboys.hellocustomview.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : Fragment(R.layout.fragment_bottom_sheet) {

   private var binding: FragmentBottomSheetBinding? = null
   private val TAG = "BottomSheetFragment"

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding = FragmentBottomSheetBinding.bind(view)
   }

   fun setOpenProgress(slideOffset: Float) {
      Log.d(TAG, "setOpenProgress: $slideOffset")
      binding?.imgArrow?.rotation = (180 * slideOffset * 3).coerceAtMost(180f)
   }

   override fun onDestroy() {
      super.onDestroy()
      binding = null
   }

}