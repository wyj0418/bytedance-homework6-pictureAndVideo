package com.wyj.pictureandvideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.ArrayList
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class PictureActivity : AppCompatActivity() {

    private val pages: MutableList<View> = ArrayList()
    private var viewPager: ViewPager? = null
    private var fullScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        title = "Image Viewer"
        for (i in netImageList.indices) {
            addImage(netImageList[i])
        }

        val viewAdapter = ViewAdapter()
        viewAdapter.setData(pages)
        viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager!!.adapter = viewAdapter
    }
    private var netImageList: List<String> = arrayListOf(
        "https://t7.baidu.com/it/u=4162611394,4275913936&fm=193&f=GIF",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F0f6cceb25745355d681316b7dec7a24617fc26e928b0b-rvZbZT_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1641872436&t=f1fb8a5a1b8a93da8f62892b8e484e58",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01b42858a7bd7aa801219c773df8f7.gif&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1641872572&t=a213d67edc90c3722b919c9e2acdfb46",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2F53%2F0a%2Fda%2F530adad966630fce548cd408237ff200.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1641872595&t=1effa0587c7a02ccad18a002d9e3b88a"
    )

    private fun addImage(imageURL: String) {
        val imageHolder: ImageView = layoutInflater.inflate(R.layout.acticity_image, null).findViewById<ImageView>(R.id.image_holder)
        Glide.with(this)
            .load(imageURL)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)) // Cache On
            .error(R.drawable.error)
            .into(imageHolder)

        pages.add(imageHolder)
    }
}

class ViewAdapter: PagerAdapter() {
    private var dataList: List<View> = ArrayList()

    override fun getCount(): Int {
        return dataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = dataList[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(dataList[position])
    }

    fun setData(list: List<View>) {
        dataList = list
        notifyDataSetChanged()
    }
}
