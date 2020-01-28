package com.example.helloandroid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class DividerItemDecoration(context: Context?) : ItemDecoration() {

    private var drawable: Drawable? = null

    init {
        context?.let {
            drawable = ContextCompat.getDrawable(context, R.drawable.divider_list_item)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(0, 0, 0, 4)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        drawable?.let {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            val childCount = parent.childCount

            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                val params: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + it.intrinsicHeight
                it.setBounds(left, top, right, bottom)
                if ((parent.getChildAdapterPosition(
                        child) == parent.adapter!!.itemCount - 1) && parent.bottom < bottom) {
                    parent.setPadding(parent.paddingLeft, parent.paddingTop, parent.paddingRight,
                        bottom - parent.bottom)
                }
                it.draw(c)
            }

        }
    }

}