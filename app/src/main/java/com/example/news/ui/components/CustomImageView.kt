package com.example.news.ui.components

import android.R.attr
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.example.news.R

import android.graphics.drawable.BitmapDrawable
import android.R.attr.path

import android.R.attr.radius

import android.graphics.RectF




class CustomImageView : androidx.appcompat.widget.AppCompatImageView {
    private val radius = 18.0f
    private var path: Path? = null
    private var rect: RectF? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        path = Path()
        rect = RectF(0F, 0F, this.width.toFloat(), this.height.toFloat())

    }

    override fun onDraw(canvas: Canvas) {
        path!!.addRoundRect(rect!!, radius, radius, Path.Direction.CW)
        canvas.clipPath(path!!)
        super.onDraw(canvas)
    }
}
