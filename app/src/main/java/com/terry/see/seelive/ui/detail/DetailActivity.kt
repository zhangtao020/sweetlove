package com.terry.see.seelive.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar

import com.terry.see.seelive.R
import com.terry.see.seelive.SeeApp
import com.terry.see.seelive.bean.ItemRecommend
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

/**
 * Created by terry on 2017/8/23.
 */
class DetailActivity : AppCompatActivity() {

    var fromSource:String? = ""
    lateinit var detailData:ItemRecommend

    lateinit var pDetail:PresenterDetail
        @Inject set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initInjector()

        fromSource = intent.getStringExtra("from")
        detailData = intent.getParcelableExtra("data")


        if (fromSource == "comment") setTitle("评论") else setTitle("详情")
        setSupportActionBar(detail_toolbar as Toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        val adapter = DetailAdapter(this,detailData)
        detail_recyclerview.adapter = adapter
        detail_recyclerview.layoutManager = LinearLayoutManager(this)

        adapter.setFragmentTransaction(supportFragmentManager.beginTransaction())

//        pDetail.requestComment(adapter,detailData.id)
        pDetail.requestComment(adapter,detailData.id,"0-20")
    }

    fun initInjector(){
        (application as SeeApp).getAppComponent().
                detailActivityComponent().activity(this).
                build().inject(this)
    }


    interface OnVideoListener{

        fun onStart(url:String)
        fun onStop()
    }
}
