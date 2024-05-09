package com.steppi.steppifitness.network

import android.content.Context
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STApplication
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object STRetrofitClient {
    fun create(context: Context): STAPIServices {
//        val instabugOkhttpInterceptor = InstabugOkhttpInterceptor()
        val logInterceptor = STLoggingInterceptor(context)
        logInterceptor.setLevel(STLoggingInterceptor.Level.BODY)

         val interceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }


        val client = OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor).addInterceptor(interceptor)
//            .addInterceptor(instabugOkhttpInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL.plus(STAPIConstants.API_VERSION))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create()).client(client)
            .build()
        return retrofit.create(STAPIServices::class.java)
    }

    fun createFitbitClient(): STAPIServices {
        val client = OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.fitbit.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create()).client(client)
            .build()
        return retrofit.create(STAPIServices::class.java)
    }

    class NoConnectivityException(private val context: Context) : IOException() {
        override fun getLocalizedMessage(): String {
            return context.getString(R.string.check_internet_connection)
        }
    }
}
