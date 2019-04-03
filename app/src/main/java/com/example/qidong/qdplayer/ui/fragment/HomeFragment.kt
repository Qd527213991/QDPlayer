package com.example.qidong.qdplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.qidong.qdplayer.base.BaseFragment

/**
 * @author  qidong
 * @date 2019/4/3
 * desc
 */
class HomeFragment:BaseFragment() {

    override fun initView(): View? {
        val tv=TextView(context)
        tv.gravity=Gravity.CENTER
        tv.setTextColor(Color.RED)
        tv.text=javaClass.simpleName
        return tv
    }
}