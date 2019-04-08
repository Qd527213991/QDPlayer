package com.example.qidong.qdplayer.presenter.impl

import com.example.qidong.qdplayer.presenter.interf.HomePresenter
import com.example.qidong.qdplayer.util.URLProviderUtils
import com.example.qidong.qdplayer.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import com.itheima.player.util.ThreadUtil
import okhttp3.*
import java.io.IOException

/**
 * @author  qidong
 * @date 2019/4/8
 * desc
 */
class HomePresenterImpl(var homeView:HomeView):HomePresenter {

    override fun loadDatas() {
        ThreadUtil.runOnMainThread(Runnable {
            val path = URLProviderUtils.getHomeUrl(0, 20)
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url(path)
                    .get()
                    .build()
            client.newCall(request).enqueue(object : Callback {
                /**
                 * 子线程调用
                 */
                override fun onFailure(call: Call?, e: IOException?) {
                    ThreadUtil.runOnMainThread(Runnable {
                        homeView.onError(e?.message)
                    })
                }

                /**
                 * 子线程调用
                 */
                override fun onResponse(call: Call?, response: Response?) {
                    val result = response?.body()?.string()
                    val gson = Gson()
                    val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                    //刷新列表
                    ThreadUtil.runOnMainThread(Runnable {
                        homeView.loadSuccess(list)
                    })


                }
            })
        })

    }

    override fun loadMore(offSet: Int) {
        ThreadUtil.runOnMainThread(Runnable {
            val path = URLProviderUtils.getHomeUrl(offSet, 20)
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url(path)
                    .get()
                    .build()
            client.newCall(request).enqueue(object : Callback {
                /**
                 * 子线程调用
                 */
                override fun onFailure(call: Call?, e: IOException?) {
                    ThreadUtil.runOnMainThread(Runnable {
                        homeView.onError(e?.message)
                    })

                }

                /**
                 * 子线程调用
                 */
                override fun onResponse(call: Call?, response: Response?) {

                    val result = response?.body()?.string()
                    val gson = Gson()
                    val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                    ThreadUtil.runOnMainThread(Runnable {
                        homeView.loadMore(list)
                    })


                }
            })
        })

    }

}