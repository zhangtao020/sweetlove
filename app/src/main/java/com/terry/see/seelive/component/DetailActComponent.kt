package com.terry.see.seelive.component

import android.app.Activity
import com.terry.see.seelive.modul.FragmentBindModule
import com.terry.see.seelive.scope.ActivityScope
import com.terry.see.seelive.ui.detail.DetailActivity
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Created by terry on 2017/8/24.
 */
@ActivityScope
@Subcomponent
interface DetailActComponent {

    fun inject(act: DetailActivity)

    @Subcomponent.Builder
    interface Builder{

        @BindsInstance
        fun activity(act: Activity):Builder

        fun build(): DetailActComponent
    }

}