package com.terry.see.seelive.component

import com.terry.see.seelive.scope.FragmentScope
import com.terry.see.seelive.ui.find.FindFragment
import dagger.Subcomponent

/**
 * Created by terry on 2017/8/22.
 */
@FragmentScope
@Subcomponent
interface RecommendFragmentComponent {

    fun inject(fragment: FindFragment)

//    val act :Activity

    @Subcomponent.Builder
    interface Builder{
        fun build():RecommendFragmentComponent
    }
}