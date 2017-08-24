package com.terry.see.seelive.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar

import com.terry.see.seelive.R
import com.terry.see.seelive.bean.ItemRecommend
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by terry on 2017/8/23.
 */
class DetailActivity : AppCompatActivity() {

    var fromSource:String? = ""
    lateinit var detailData:ItemRecommend


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        fromSource = intent.getStringExtra("from")
        detailData = intent.getParcelableExtra("data")


        if (fromSource == "comment") setTitle("评论") else setTitle("详情")
        setSupportActionBar(detail_toolbar as Toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        detail_recyclerview.adapter =

    }
}
