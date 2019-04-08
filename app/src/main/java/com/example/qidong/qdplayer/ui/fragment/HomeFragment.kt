package com.example.qidong.qdplayer.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.qidong.qdplayer.R
import com.example.qidong.qdplayer.adapter.HomeAdapter
import com.example.qidong.qdplayer.base.BaseFragment
import com.example.qidong.qdplayer.util.URLProviderUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import com.itheima.player.util.ThreadUtil
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

/**
 * @author  qidong
 * @date 2019/4/3
 * desc
 */
class HomeFragment:BaseFragment() {

    private val adapter by lazy { HomeAdapter() }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener() {
        //初始化RecyclerView
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter

        // 初始化刷新控件
        refreshLayout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.GREEN)
        refreshLayout.setOnRefreshListener {
            loadDatas()
        }
        //监听RecyclerView的滑动
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(newState==RecyclerView.SCROLL_STATE_IDLE) {
                    //最后一条已经显示
                    val layoutManager=recyclerView.layoutManager
                    if(layoutManager is LinearLayoutManager) {
                        val manager:LinearLayoutManager=layoutManager
                        val lastPosition=manager.findLastVisibleItemPosition()
                        if(lastPosition==adapter.itemCount-1) {
                            //最后一条已经显示了
                            loadMore(adapter.itemCount-1)
                        }
                    }

                }
            }
        })


    }

    override fun initData() {
        loadDatas()
    }

    private fun loadDatas() {
        val path=URLProviderUtils.getHomeUrl(0,20)
        val client=OkHttpClient()
        val request=Request.Builder()
                .url(path)
                .get()
                .build()
        client.newCall(request).enqueue(object:Callback{
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call?, e: IOException?) {
                ThreadUtil.runOnMainThread(Runnable {
                    refreshLayout.isRefreshing=false
                })
                myToast("获取数据出错")
                println("获取信息出错:${Thread.currentThread().name}")
            }

            /**
             * 子线程调用
             */
            override fun onResponse(call: Call?, response: Response?) {
                ThreadUtil.runOnMainThread(Runnable {
                    refreshLayout.isRefreshing=false
                })
                myToast("获取数据成功")
                val result=response?.body()?.string()
                val gson= Gson()
                val list=gson.fromJson<List<HomeItemBean>>(result,object :TypeToken<List<HomeItemBean>>(){}.type)
                println("获取信息成功:${list.size}")
                //刷新列表
                ThreadUtil.runOnMainThread(object:Runnable{
                    override fun run() {
                        adapter.updateList(list)
                    }
                })
            }
        })
    }

    private fun loadMore(offSet:Int) {
        val path=URLProviderUtils.getHomeUrl(offSet,20)
        val client=OkHttpClient()
        val request=Request.Builder()
                .url(path)
                .get()
                .build()
        client.newCall(request).enqueue(object:Callback{
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call?, e: IOException?) {
                ThreadUtil.runOnMainThread(Runnable {
                    refreshLayout.isRefreshing=false
                })
                myToast("获取数据出错")
                println("获取信息出错:${Thread.currentThread().name}")
            }

            /**
             * 子线程调用
             */
            override fun onResponse(call: Call?, response: Response?) {
                ThreadUtil.runOnMainThread(Runnable {
                    refreshLayout.isRefreshing=false
                })
                myToast("获取数据成功")
                val result=response?.body()?.string()
                val gson= Gson()
                val list=gson.fromJson<List<HomeItemBean>>(result,object :TypeToken<List<HomeItemBean>>(){}.type)
                println("获取信息成功:${list.size}")
                //刷新列表
                ThreadUtil.runOnMainThread(object:Runnable{
                    override fun run() {
                        adapter.loadMore(list)
                    }
                })
            }
        })
    }


}