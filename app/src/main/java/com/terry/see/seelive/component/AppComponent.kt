package com.terry.see.seelive.component

import android.content.Context
import com.terry.see.seelive.modul.ActivityBindModule
import com.terry.see.seelive.modul.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by terry on 2017/8/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ActivityBindModule::class))
interface AppComponent {

    var appContext:Context

    fun mainActivityComponent():MainActComponent.Builder
}