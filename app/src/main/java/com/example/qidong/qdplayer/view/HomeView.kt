package com.example.qidong.qdplayer.view

import com.itheima.player.model.bean.HomeItemBean

/**
 * @author  qidong
 * @date 2019/4/8
 * desc Home界面和Presenter交互
 */
interface HomeView {

    fun onError(message:String?)
    fun loadSuccess(list:List<HomeItemBean>?)
    fun loadMore(list:List<HomeItemBean>?)

}