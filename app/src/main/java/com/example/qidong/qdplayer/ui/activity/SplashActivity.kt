package com.example.qidong.qdplayer.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import com.example.qidong.qdplayer.R
import com.example.qidong.qdplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @author  qidong
 * @date 2019/4/2
 * desc
 */
class SplashActivity:BaseActivity(),ViewPropertyAnimatorListener{
    override fun onAnimationEnd(view: View?) {
       startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {

    }

    override fun onAnimationStart(view: View?) {

    }


    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {

        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).duration=2000
    }
}