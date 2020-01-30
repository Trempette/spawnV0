package fr.webedia.spawn.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.webedia.spawn.R

@BindingAdapter("drawable")
fun setDrawable(imageView: ImageView, drawable: Drawable?) {
    if (drawable != null) {
        drawable.mutate()
        imageView.setImageDrawable(drawable)
    }
}

@BindingAdapter("roundedImageUrl")
fun ImageView.loadRoundedImageUrl(url: String?) {
    if (url != null) {
        Glide.with(this).load(url).placeholder(R.drawable.background).error(R.drawable.background).apply(
            RequestOptions.circleCropTransform()).into(this)
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