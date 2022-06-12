package app.presentation.components

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import app.presentation.R
import timber.log.Timber

open class AdvTextView : AppCompatTextView {
    constructor(context: Context) : super(context) {
        customFont(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
    ) : super(context, attrs) {
        customFont(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int,
    ) : super(context, attrs, defStyle) {
        customFont(context, attrs)
    }

    @SuppressLint("ResourceType")
    private fun customFont(
        context: Context,
        attrs: AttributeSet?,
    ) {
        var fontPath: String? = null
        var typedArray: TypedArray? = null
        try {
            if (attrs != null) {
                typedArray = context.obtainStyledAttributes(attrs, R.styleable.AdvTextView)
            }
            if (typedArray != null && typedArray.hasValue(R.styleable.AdvTextView_customFont)) {
                fontPath = typedArray.getString(R.styleable.AdvTextView_customFont)
            }
            val typeface = fontPath?.let {
                Typeface.createFromAsset(
                    context.assets,
                    it
                )
            } ?: ResourcesCompat.getFont(context, R.string.font_regular)
            setTypeface(typeface)
            includeFontPadding = true
        } catch (ignored: Throwable) {
            Timber.d(ignored.localizedMessage)
        } finally {
            typedArray?.recycle()
        }
    }

    open fun setFont(font: Int) {
        try {
            val typeface = Typeface.createFromAsset(context.assets, context.getString(font))
            setTypeface(typeface)
        } catch (ignored: Exception) {
        }
    }
}