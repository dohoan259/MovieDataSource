package com.example.moviedatasource.di

import android.content.Context
import com.example.moviedatasource.BuildConfig
import com.example.moviedatasource.data.remote.SafeRfc3339DateJsonAdapter
import com.example.moviedatasource.data.remote.interceptor.ApiKeyInterceptor
import com.example.moviedatasource.data.remote.service.AccountService
import com.example.moviedatasource.data.remote.service.MovieService
import com.example.moviedatasource.data.remote.service.SearchService
import com.example.moviedatasource.data.remote.service.discovery.DiscoveryService
import com.example.moviedatasource.utils.Config.BASE_URL
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    fun provideApiKeyInterceptor(apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey = apiKey)

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)


    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, 50 * 1024 * 1024)
    }

    @Provides
    fun provideOkHttpClient(
        cache: Cache,
        apiKeyInterceptor: ApiKeyInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .dns(Dns.SYSTEM)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)
            .also {
                if (BuildConfig.DEBUG)
                    it.addInterceptor(httpLoggingInterceptor)
            }
            .cache(cache)
            .build()
    }

    @Provides
    fun provideMoshiConverter(): Converter.Factory {
        val adapter = SafeRfc3339DateJsonAdapter(Rfc3339DateJsonAdapter()).nullSafe()
        return Moshi.Builder()
            .add(Date::class.java, adapter)
            .add(KotlinJsonAdapterFactory())
            .build()
            .let { moshi -> MoshiConverterFactory.create(moshi) }
    }

    @Provides
    fun provideCallAdapter(): CallAdapter.Factory {
        return NetworkResponseAdapterFactory()
    }

    @Provides
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        callFactory: CallAdapter.Factory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(callFactory)
            .addConverterFactory(converterFactory)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun bindingMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    fun bindingAccountService(retrofit: Retrofit): AccountService =
        retrofit.create(AccountService::class.java)

    @Provides
    fun bindingSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Provides
    fun bindingDiscoveryService(retrofit: Retrofit): DiscoveryService =
        retrofit.create(DiscoveryService::class.java)
}