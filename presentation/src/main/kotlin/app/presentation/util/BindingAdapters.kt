package app.presentation.util

import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.presentation.components.AdvTextView
import app.presentation.extension.gone
import app.presentation.extension.visible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("showHideViaDomain")
    fun showHideViaDomain(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("loadImageDrawable")
    fun loadImageDrawable(imageView: ImageView, url: Int) {
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, url))
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun bindLoadImageUrl(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context).load(it).into(imageView)
        }
    }


    @JvmStatic
    @BindingAdapter("overLineFlag")
    fun addTextStrikeTextFlag(floTextView: AdvTextView, boolean: Boolean) {
        if (boolean) {
            floTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG or floTextView.paintFlags
        }
    }


    @JvmStatic
    @BindingAdapter("imageFromUrl")
    fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("imageFromUrlLoadCircle")
    fun bindImageFromUrlLoadCircle(view: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(imageUrl)
                .transform(CircleCrop())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }


    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("itemDecoration")
    fun bindAdapter(view: RecyclerView, decoration: RecyclerView.ItemDecoration) {
        view.addItemDecoration(decoration)
    }


    @JvmStatic
    @BindingAdapter("isVisibleRatingView")
    fun isVisibleRatingView(view: View, isVisible: Boolean) {
        if (isVisible) {
            view.visible()
        } else {
            view.gone()
        }
    }

}