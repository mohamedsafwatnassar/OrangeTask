package com.orange.newsapp.utils

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.orange.newsapp.R

fun View.gone() = run { visibility = View.GONE }

fun View.visible() = run { visibility = View.VISIBLE }

fun View.invisible() = run { visibility = View.INVISIBLE }

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

@SuppressLint("ResourceType")
fun ImageView.loadImage(@IdRes resource: Int) {
    Glide.with(this.context)
        .load(resource)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .into(this)
}

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

fun String.isEmpty(): Boolean {
    return (
        TextUtils.isEmpty(this.trim()) ||
            this.trim().equals("", ignoreCase = true) ||
            this.trim().equals("{}", ignoreCase = true) ||
            this.trim().equals("null", ignoreCase = true) ||
            this.trim().equals("undefined", ignoreCase = true)
        )
}
