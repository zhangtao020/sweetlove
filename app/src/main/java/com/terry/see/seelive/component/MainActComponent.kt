package com.terry.see.seelive.component

import android.app.Activity
import com.terry.see.seelive.modul.FragmentBindModule
import com.terry.see.seelive.scope.ActivityScope
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Created by terry on 2017/8/22.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(FragmentBindModule::class))
interface MainActComponent {

    @Subcomponent.Builder
    interface Builder{

        @BindsInstance
        fun activity(act: Activity):Builder

        fun build():MainActComponent
    }

    fun recomendFragmentComponent():RecommendFragmentComponent.Builder
}