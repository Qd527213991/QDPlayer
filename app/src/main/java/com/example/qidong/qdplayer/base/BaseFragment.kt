package com.example.qidong.qdplayer.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * @author  qidong
 * @date 2019/4/2
 * desc
 */
abstract class BaseFragment:Fragment(),AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

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
     * 获取布局View
     */
    abstract fun initView(): View?

    /**
     * 初始化
     */
    protected fun init() {
        
    }

    fun myToast(msg:String) {
        context?.runOnUiThread { toast(msg) }
    }
    
    
}