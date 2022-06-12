package app.presentation.products.virtualcv

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import app.presentation.base.adapter.RecyclerItem

data class GalleryUI(override val id: Long, @DrawableRes val drawable: Int) : RecyclerItem {

}