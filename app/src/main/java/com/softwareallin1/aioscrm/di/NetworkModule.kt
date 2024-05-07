package com.softwareallin1.aioscrm.di

import com.softwareallin1.aioscrm.BuildConfig
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.HttpHandleIntercept
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {
    /**
     * Generate Retrofit Client
     */


    //private external fun getDevBaseUrl(): String
    //private external fun getProdBaseUrl(): String


    @Provides
    @RetrofitStore
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        System.loadLibrary("keys")
        //var baseUrl: String = ""
        val baseUrl = when (BuildConfig.FLAVOR) {
            //https://crm.amazonsellers.in/mapi/lead_status_change_post
            "stag" -> {
                "https://crm.softwareallin1.com/mapi/"
                // String(Base64.decode(getDevBaseUrl(), Base64.DEFAULT))
            }

            "prod" -> {
                "https://crm.amazonsellers.in/mapi/"
                //String(Base64.decode(getProdBaseUrl(), Base64.DEFAULT))
            }

            else -> {
                "https://crm.softwareallin1.com/mapi/"
                //String(Base64.decode(getDevBaseUrl(), Base64.DEFAULT))
            }
        }
        val builder = Retrofit.Builder()
        builder.baseUrl(baseUrl)
        builder.addConverterFactory(JacksonConverterFactory.create())
        builder.client(okHttpClient)
        return builder.build()
    }

    @Provides
    @ViewModelScoped
    fun provideApiInterface(@RetrofitStore retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)


    @Provides
    fun provideHttpHandleIntercept(): HttpHandleIntercept = HttpHandleIntercept()


    /**
     * generate OKhttp client
     */
    @Provides
    fun getOkHttpClient(httpHandleIntercept: HttpHandleIntercept): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logging)
        builder.readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpHandleIntercept).build()

        return builder.build()
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RetrofitStore