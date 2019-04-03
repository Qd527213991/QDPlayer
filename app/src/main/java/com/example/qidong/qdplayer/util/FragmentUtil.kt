package com.example.qidong.qdplayer.util

import com.example.qidong.qdplayer.R
import com.example.qidong.qdplayer.base.BaseFragment
import com.example.qidong.qdplayer.ui.fragment.HomeFragment
import com.example.qidong.qdplayer.ui.fragment.MvFragment
import com.example.qidong.qdplayer.ui.fragment.VBangFragment
import com.example.qidong.qdplayer.ui.fragment.YueDanFragment

/**
 * @author  qidong
 * @date 2019/4/3
 * desc 管理Fragment的Util类
 */
class FragmentUtil private constructor(){  //私有构造函数

    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vbangFragment by lazy { VBangFragment() }
    val yuedanFragment by lazy { YueDanFragment() }

    companion object {
        val fragmentUtil by lazy { FragmentUtil() }
    }

    /**
     * 根据tabId获取对应的Fragment
     */
    fun getFragment(tabId:Int):BaseFragment? {
        when(tabId) {
            R.id.tab_main ->return homeFragment
            R.id.tab_mv ->return mvFragment
            R.id.tab_vbang ->return vbangFragment
            R.id.tab_joy_order->return yuedanFragment
        }

        return null
    }



}