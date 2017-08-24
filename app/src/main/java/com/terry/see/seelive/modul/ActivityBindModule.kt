package com.terry.see.seelive.modul

import com.terry.see.seelive.component.DetailActComponent
import com.terry.see.seelive.component.MainActComponent
import dagger.Module

/**
 * Created by terry on 2017/8/22.
 */
@Module(subcomponents = arrayOf(MainActComponent::class, DetailActComponent::class))
class ActivityBindModule