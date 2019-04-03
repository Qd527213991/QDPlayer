package com.example.qidong.qdplayer.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.example.qidong.qdplayer.R
import com.example.qidong.qdplayer.base.BaseActivity
import com.example.qidong.qdplayer.util.ToolbarManager
import org.jetbrains.anko.find

/**
 * @author  qidong
 * @date 2019/4/3
 * desc
 */
class SettingActivity :BaseActivity(),ToolbarManager{

    //惰性加载
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        initSettingToolBar()
        //获取推送通知有没有被选中
        val sp=PreferenceManager.getDefaultSharedPreferences(this)
        val push=sp.getBoolean("push",false)
        Log.e("SettingActivity","push="+push)
    }
}