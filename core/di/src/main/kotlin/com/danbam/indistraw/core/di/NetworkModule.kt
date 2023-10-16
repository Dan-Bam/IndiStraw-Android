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
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI = retrofit.create(AuthAPI::class.java)

    @Provides
    fun provideAccountAPI(retrofit: Retrofit): AccountAPI = retrofit.create(AccountAPI::class.java)

    @Provides
    fun provideFileAPI(retrofit: Retrofit): FileAPI = retrofit.create(FileAPI::class.java)

    @Provides
    fun provideAddressAPI(retrofit: Retrofit): AddressAPI = retrofit.create(AddressAPI::class.java)

    @Provides
    fun provideCrowdFundingAPI(retrofit: Retrofit): CrowdFundingAPI =
        retrofit.create(CrowdFundingAPI::class.java)

    @Provides
    fun provideSearchAPI(retrofit: Retrofit): SearchAPI = retrofit.create(SearchAPI::class.java)

    @Provides
    fun provideQRCodeAPI(retrofit: Retrofit): QRCodeAPI = retrofit.create(QRCodeAPI::class.java)

    @Provides
    fun provideMovieAPI(retrofit: Retrofit): MovieAPI = retrofit.create(MovieAPI::class.java)

    @Provides
    fun provideFundingAPI(retrofit: Retrofit): FundingAPI = retrofit.create(FundingAPI::class.java)

    @Provides
    fun provideBannerAPI(retrofit: Retrofit): BannerAPI = retrofit.create(BannerAPI::class.java)
}