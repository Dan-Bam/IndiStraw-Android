package com.danbam.indistraw.core.di

import android.content.Context
import com.danbam.indistraw.core.remote.api.AccountAPI
import com.danbam.indistraw.core.remote.api.AddressAPI
import com.danbam.indistraw.core.remote.api.AuthAPI
import com.danbam.indistraw.core.remote.api.BannerAPI
import com.danbam.indistraw.core.remote.api.CrowdFundingAPI
import com.danbam.indistraw.core.remote.api.FileAPI
import com.danbam.indistraw.core.remote.api.FundingAPI
import com.danbam.indistraw.core.remote.api.MovieAPI
import com.danbam.indistraw.core.remote.api.QRCodeAPI
import com.danbam.indistraw.core.remote.api.SearchAPI
import com.danbam.indistraw.core.data.interceptor.IndiStrawInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideOkHttpclient(
        @ApplicationContext context: Context,
        indiStrawInterceptor: IndiStrawInterceptor
    ): OkHttpClient {
        IndiStrawInterceptor.context = context
        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, 20L * 1024 * 1024))
            .addInterceptor(indiStrawInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideAuthAPI(retrofit: Retrofit): com.danbam.indistraw.core.remote.api.AuthAPI = retrofit.create(
        com.danbam.indistraw.core.remote.api.AuthAPI::class.java)

    @Provides
    fun provideAccountAPI(retrofit: Retrofit): com.danbam.indistraw.core.remote.api.AccountAPI = retrofit.create(
        com.danbam.indistraw.core.remote.api.AccountAPI::class.java)

    @Provides
    fun provideFileAPI(retrofit: Retrofit): com.danbam.indistraw.core.remote.api.FileAPI = retrofit.create(
        com.danbam.indistraw.core.remote.api.FileAPI::class.java)

    @Provides
    fun provideAddressAPI(retrofit: Retrofit): com.danbam.indistraw.core.remote.api.AddressAPI = retrofit.create(
        com.danbam.indistraw.core.remote.api.AddressAPI::class.java)

    @Provides
    fun provideCrowdFundingAPI(retrofit: Retrofit): com.danbam.indistraw.core.remote.api.CrowdFundingAPI =
        retrofit.create(com.danbam.indistraw.core.remote.api.CrowdFundingAPI::class.java)

    @Provides
    fun provideSearchAPI(retrofit: Retrofit): com.danbam.indistraw.core.remote.api.SearchAPI = retrofit.create(
        com.danbam.indistraw.core.remote.api.SearchAPI::class.java)

    @Provides
    fun provideQRCodeAPI(retrofit: Retrofit): com.danbam.indistraw.core.remote.api.QRCodeAPI = retrofit.create(
        com.danbam.indistraw.core.remote.api.QRCodeAPI::class.java)

    @Provides
    fun provideMovieAPI(retrofit: Retrofit): com.danbam.indistraw.core.remote.api.MovieAPI = retrofit.create(
        com.danbam.indistraw.core.remote.api.MovieAPI::class.java)

    @Provides
    fun provideFundingAPI(retrofit: Retrofit): com.danbam.indistraw.core.remote.api.FundingAPI = retrofit.create(
        com.danbam.indistraw.core.remote.api.FundingAPI::class.java)

    @Provides
    fun provideBannerAPI(retrofit: Retrofit): com.danbam.indistraw.core.remote.api.BannerAPI = retrofit.create(
        com.danbam.indistraw.core.remote.api.BannerAPI::class.java)
}