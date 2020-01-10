package com.example.helloandroid.timerecording

import android.content.Context
import com.example.helloandroid.NetworkConnectionInterceptor
import com.example.helloandroid.timerecording.web.TeamUpApi
import com.example.helloandroid.timerecording.web.TeamupCalendarConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamupServiceGenerator {


    companion object {
        private var teamupApi: TeamUpApi? = null
        private const val BASE_URL = TeamupCalendarConfig.BASE_URL

        fun getTeamUpApi(context: Context): TeamUpApi {
            if (teamupApi == null) {
                val httpLoggingIntercepter =
                    HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
                val networkConnectionInterceptor = NetworkConnectionInterceptor(context)
                val okHttpClient = OkHttpClient.Builder().apply {
                    this.addInterceptor(networkConnectionInterceptor)
                    this.addInterceptor(httpLoggingIntercepter)
                }.build()

                val retrofitBuilder = Retrofit.Builder()//
                    .baseUrl(BASE_URL)//
                    .addConverterFactory(GsonConverterFactory.create())//
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                teamupApi =  retrofitBuilder.client(okHttpClient).build().create(TeamUpApi::class.java)
            }
            return teamupApi!!
        }

    }


}