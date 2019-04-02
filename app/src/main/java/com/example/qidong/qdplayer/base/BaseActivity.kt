package com.example.qidong.qdplayer.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * @author  qidong
 * @date 2019/4/2
 * desc
 */
abstract class BaseActivity :AppCompatActivity(),AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()

    }

    /**
     * 获取布局id
     */
    abstract fun getLayoutId():Int

    /**
     * 初始化数据
     */
    open protected fun initData() {

    }

    /**
     * 设置监听器
     */
    open protected fun initListener() {

    }

    /**
     * toast
     */
    fun myToast(msg:String) {
        runOnUiThread{toast(msg)}
    }

    /**
     * 页面跳转时关闭页面
     */
    inline fun <reified T:BaseActivity>startActivityAndFinish() {
        startActivity<T>()
        finish()
    }


}