package com.example.qidong.qdplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.qidong.qdplayer.R
import com.itheima.player.model.bean.HomeItemBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * @author  qidong
 * @date 2019/4/8
 * desc  通过Alt+Insert进行快速生成构造函数
 */
class HomeItemView:RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    /**
     * 初始化方法
     */
    init {
        View.inflate(context, R.layout.item_home,this)
    }

    /**
     * 刷新条目View数据
     */
    fun setData(data: HomeItemBean) {
        //歌曲名称
        title.text=data.title
        //简介
        desc.text=data.description
        //背景图片
        Picasso.with(context).load(data.posterPic).into(bg)


    }
}