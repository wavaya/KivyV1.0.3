package xyz.wayhua.kivyv103.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

import com.readystatesoftware.chuck.ChuckInterceptor

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.Interceptor

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import xyz.wayhua.kivyv103.BuildConfig
import xyz.wayhua.kivyv103.data.repository.remote.IService

import java.util.concurrent.TimeUnit

/**
 * Remote Web Service datasource
 */
val remoteDatasourceModule = module {
    // provided web components
    single { ChuckInterceptor(androidApplication()) }
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single { createOkHttpClient(get<ChuckInterceptor>(), get<HttpLoggingInterceptor>()) }

    // Fill property
    single { createWebService<IService>(get(), BASE_URL) }
}

fun createOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .apply {
            if (BuildConfig.DEBUG) {
                for (intercept in interceptors) {
                    addInterceptor(intercept)
                }
            }
        }
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val contentType = "application/json".toMediaTypeOrNull()!!
//    Json(
//        JsonConfiguration.Default.copy(
//            ignoreUnknownKeys = true,
//            isLenient = true,
//            serializeSpecialFloatingPointValues = true,
//            useArrayPolymorphism = true
//        )
//    )


    val factory = Json(
        JsonConfiguration.Default.copy(
            ignoreUnknownKeys = true, //这个地方比较特殊，有时json有的字段，定义的类没有会报错，非要修改为true
            isLenient = true,
            serializeSpecialFloatingPointValues = true,
            useArrayPolymorphism = true
        )// 一直没找到怎么修改。终于找到了。
    ).asConverterFactory(contentType)
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(factory)

        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    return retrofit.create(T::class.java)
}

private const val BASE_URL = "https://kudago.com/public-api/v1.4/"