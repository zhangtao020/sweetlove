package com.terry.see.seelive

import android.app.Application
import android.content.Context
import com.terry.see.seelive.component.AppComponent
import com.terry.see.seelive.component.DaggerAppComponent
import com.terry.see.seelive.modul.AppModule

/**
 * Created by terry on 2017/8/18.
 */
class SeeApp : Application(){

    companion object {
        lateinit var appContext:Context
    }

    lateinit var appcomponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appContext = this

        initInjector()
    }

    fun initInjector(){
       appcomponent =  DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun getAppComponent():AppComponent{
        return appcomponent
    }

}