package com.example.qidong.qdplayer.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.qidong.qdplayer.R
import com.example.qidong.qdplayer.adapter.HomeAdapter
import com.example.qidong.qdplayer.base.BaseFragment
import com.example.qidong.qdplayer.presenter.impl.HomePresenterImpl
import com.example.qidong.qdplayer.util.URLProviderUtils
import com.example.qidong.qdplayer.view.HomeView
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
class HomeFragment : BaseFragment(), HomeView {

    private val adapter by lazy { HomeAdapter() }
    private val presenter by lazy { HomePresenterImpl(this) }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initListener() {
        //初始化RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // 初始化刷新控件
        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        refreshLayout.setOnRefreshListener {
            presenter.loadDatas()
        }
        //监听RecyclerView的滑动
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //最后一条已经显示
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val manager: LinearLayoutManager = layoutManager
                        val lastPosition = manager.findLastVisibleItemPosition()
                        if (lastPosition == adapter.itemCount - 1) {
                            //最后一条已经显示了
                            presenter.loadMore(adapter.itemCount-1)
                        }
                    }

                }
            }
        })


    }

    override fun initData() {
       presenter.loadDatas()
    }


    override fun onError(message: String?) {
        refreshLayout.isRefreshing = false
        myToast("数据请求错误")

    }

    override fun loadSuccess(list: List<HomeItemBean>?) {
        refreshLayout.isRefreshing = false
        adapter.updateList(list)


    }

    override fun loadMore(list: List<HomeItemBean>?) {
        refreshLayout.isRefreshing = false
        adapter.loadMore(list)

    }


}