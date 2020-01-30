package fr.webedia.spawn.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter("drawable")
fun setDrawable(imageView: ImageView, drawable: Drawable?) {
    if (drawable != null) {
        drawable.mutate()
        imageView.setImageDrawable(drawable)
    }
}

@BindingMethods(

    BindingMethod(
        type = BottomNavigationView::class,
        attribute = "onNavigationItemSelected",
        method = "setOnNavigationItemSelectedListener"
    )
)

class BindingAdapters