package com.example.helloandroid.timerecording

import com.example.helloandroid.timerecording.view.TeamUpApi
import com.example.helloandroid.timerecording.web.TeamupCalenderConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TeamupServiceGenerator {

    private const val BASE_URL = TeamupCalenderConfig.BASE_URL
    private val httpLoggingIntercepter =
        HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }

    private val okHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(httpLoggingIntercepter)
    }

    private val retrofitBuilder = Retrofit.Builder()//
        .baseUrl(BASE_URL)//
        .addConverterFactory(GsonConverterFactory.create())//
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    private val retrofit = retrofitBuilder.client(okHttpClient.build()).build()

    fun createService(): TeamUpApi {
        return retrofit.create(TeamUpApi::class.java)
    }


}