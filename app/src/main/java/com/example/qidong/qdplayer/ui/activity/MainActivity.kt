package com.example.qidong.qdplayer.ui.activity

import android.support.v7.widget.Toolbar
import android.util.Log
import com.example.qidong.qdplayer.R
import com.example.qidong.qdplayer.base.BaseActivity
import com.example.qidong.qdplayer.util.FragmentUtil
import com.example.qidong.qdplayer.util.ToolbarManager
import com.roughike.bottombar.BottomBar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

/**
 * @author qidong
 * @date 2019-04-02
 * desc
 */
class MainActivity :BaseActivity(),ToolbarManager{

    //惰性加载
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }

    override fun initListener() {


       bottom_bar.setOnTabSelectListener { tabId ->
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(tabId),tabId.toString())
            transaction.commit()
        }



    }
}
