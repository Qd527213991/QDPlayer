package com.example.qidong.qdplayer.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.qidong.qdplayer.widget.HomeItemView
import com.example.qidong.qdplayer.widget.LoadMoreView
import com.itheima.player.model.bean.HomeItemBean

/**
 * @author  qidong
 * @date 2019/4/8
 * desc
 */
class HomeAdapter:RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    private val list=ArrayList<HomeItemBean>()

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {

        if(position==list.size) return
        val data=this.list.get(position)
        val itemView=holder.itemView as HomeItemView
        //条目刷新
        itemView.setData(data)



    }

    fun updateList(list:List<HomeItemBean>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun loadMore(list:List<HomeItemBean>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.list.size+1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):HomeHolder {
        if(viewType==1) {
            //最后一条
            return HomeHolder(LoadMoreView(parent?.context))
        } else {
            //普通条目
            return HomeHolder(HomeItemView(parent?.context))
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position==list.size) {
            return 1
        } else {
            return 0
        }
    }

    class HomeHolder(itemView: View):RecyclerView.ViewHolder(itemView)

}