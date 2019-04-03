package com.example.qidong.qdplayer.util

import android.content.Intent
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.qidong.qdplayer.R
import com.example.qidong.qdplayer.ui.activity.SettingActivity

/**
 * @author  qidong
 * @date 2019/4/3
 * desc 所有页面Toolbar的管理类
 */
interface ToolbarManager {

    val toolbar:Toolbar

    /**
     * 初始化界面中的toolbar
     */
    fun initMainToolBar() {
        toolbar.title="黑马影音"
        toolbar.inflateMenu(R.menu.main)
        //kotlin和Java调用特性
        //如果Java接口中只有一个未实现的方法  可以省略接口对象 直接用{}表示未实现的方法
        toolbar.setOnMenuItemClickListener { item ->
            when(item?.itemId) {
                R.id.setting -> {
                    //跳转至设置页面
                    toolbar.context.startActivity(Intent(toolbar.context, SettingActivity::class.java))
                }
            }
            true
        }
    }

    /**
     * 初始化设置界面的Toolbar
     */
    fun initSettingToolBar() {
        toolbar.title="设置界面"
    }
}