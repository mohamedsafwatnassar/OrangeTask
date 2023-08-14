package com.orange.newsapp.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.gone() = run { visibility = View.GONE }

fun View.visible() = run { visibility = View.VISIBLE }

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

// fun NavController.customNavigate(
//    @IdRes destinationId: Int,
//    inclusive:Boolean = false,
//    data: Bundle? = null
// ) {
//    val navOption =
//        NavOptions.Builder().apply {
//            setPopUpTo(destinationId, inclusive)
//            setEnterAnim(R.anim.slide_from_out_right_to_center)
//            setExitAnim(R.anim.slide_from_center_to_out_left)
//            setPopEnterAnim(R.anim.slide_from_out_left_to_center)
//            setPopExitAnim(R.anim.slide_from_center_to_out_right)
//        }.build()
//    navigate(destinationId, data, navOption)
// }

inline fun View.onDebouncedListener(
    delayInClick: Long = 500L,
    crossinline listener: (View) -> Unit
) {
    val enableAgain = Runnable { isEnabled = true }
    setOnClickListener {
        if (isEnabled) {
            isEnabled = false
            postDelayed(enableAgain, delayInClick)
            listener(it)
        }
    }
}
