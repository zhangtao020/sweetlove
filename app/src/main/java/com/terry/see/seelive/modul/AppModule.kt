package com.terry.see.seelive.modul

import android.content.Context
import com.terry.see.seelive.Keys
import com.terry.see.seelive.SeeApp
import com.terry.see.seelive.http.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by terry on 2017/8/18.
 */
@Module
class AppModule(var app:SeeApp) {

    @Provides
    @Singleton
    internal fun provideAppContext():Context{
        return app
    }


    @Provides
    @Singleton
    internal fun provideOkHttpClient() : OkHttpClient {

        var okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000,TimeUnit.MILLISECONDS)

        return okHttpClient.build()
    }


    @Provides
    @Singleton
    internal fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        var retrofit = Retrofit.Builder().client(okHttpClient).
                baseUrl(Keys.base_url).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(ApiService::class.java)
    }
}