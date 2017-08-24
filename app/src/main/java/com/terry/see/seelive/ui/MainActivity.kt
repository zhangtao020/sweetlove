package com.terry.see.seelive.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.terry.see.seelive.R
import com.terry.see.seelive.SeeApp
import com.terry.see.seelive.component.MainActComponent
import com.terry.see.seelive.ui.find.FindFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by terry on 2017/8/22.
 */
class MainActivity : AppCompatActivity(){

    lateinit var appComponent:MainActComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar)
        setTitle("首页")

        appComponent= (application as SeeApp).getAppComponent().mainActivityComponent().activity(this).build()


        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FindFragment.newInstance(1)).commit()
    }

}
