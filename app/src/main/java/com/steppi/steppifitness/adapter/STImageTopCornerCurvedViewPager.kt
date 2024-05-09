package com.steppi.steppifitness.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.steppi.steppifitness.R
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.loadImage
import kotlinx.android.synthetic.main.image_view_pager_top_rounded_corner.view.*

class STImageTopCornerCurvedViewPager(private val context: Context) :
    PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private var images: List<String?>? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return images?.size ?: 0
    }

    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.image_view_pager_top_rounded_corner, null)
        images?.get(position)?.let {
            v.imageViewTopRounded.loadImage(context, it)
            v.imageViewTopRounded.background = STUtils.getDrawable(context, R.drawable.bg_rounded_top_corner)
        } ?: kotlin.run {
            v.imageViewTopRounded.setBackgroundResource(R.drawable.place_holder_image)
        }
        val vp = container as ViewPager
        vp.addView(v, 0)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }

    fun setItemImage(images: List<String?>?) {
        this.images = images
        notifyDataSetChanged()
    }
}
