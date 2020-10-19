package com.cya.application.ft_home.main.home.adapter

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.ViewSizeResolver
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.chad.library.adapter.base.BaseViewHolder
import com.cya.application.ft_home.R
import com.cya.frame.adapter.BaseRecyclerAdapter
import com.cya.frame.ext.dp2Px
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.randomColor
import com.cya.frame.ext.yes
import com.cya.lib_base.entity.Article

class ArticleListAdapter : BaseRecyclerAdapter<Article>(R.layout.adapter_home_article_list) {
    override fun getLayoutManager(): GridLayoutManager {
        return GridLayoutManager(mContext, 2)
    }

    override fun convert(helper: BaseViewHolder, item: Article) {
        val tvTitle = helper.getView<TextView>(R.id.tv_title)
        val ivImage = helper.getView<ImageView>(R.id.iv_image)
        with(item) {
            tvTitle.text = title
//            ivImage.scaleType = ImageView.ScaleType.FIT_START
            val image =
                "https://upload-images.jianshu.io/upload_images/1315745-b2723d649e24bede.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/500/format/webp"
            val imageGIF = "https://img-blog.csdnimg.cn/20200314180800300.gif"
            //imageview 直接加载
            ivImage.load(image) {
                crossfade(true)
                transformations(
//                    BlurTransformation(mContext)//模糊
//                    CircleCropTransformation()//原型
                    GrayscaleTransformation()//灰色滤镜
                    //圆角
//                    RoundedCornersTransformation(
//                        mContext.dp2Px(8).toFloat(),
//                        mContext.dp2Px(8).toFloat(),
//                        mContext.dp2Px(8).toFloat(),
//                        mContext.dp2Px(8).toFloat()
//                    )
                )
            }
//            ivImage.setBackgroundColor(randomColor())
            //非imageview加载
//            val imageLoader = ImageLoader(mContext)
//            val request = ImageRequest.Builder(mContext)
//                .data(image)
//                .target {
//                    ivImage.setImageDrawable(it)
//                }
//                .build()
//            imageLoader.enqueue(request)

            //gif
//            val imageLoader = ImageLoader.Builder(mContext)
//                .componentRegistry {
//                    if(Build.VERSION.SDK_INT >= 28) {
//                        add(ImageDecoderDecoder())
//                    }else {
//                        add(GifDecoder())
//                    }
//                }.build()
//            ivImage.load(imageGIF, imageLoader)
        }
    }

}