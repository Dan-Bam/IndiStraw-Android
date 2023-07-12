package com.danbam.di

import android.util.Log
import com.danbam.data.remote.api.AccountAPI
import com.danbam.data.remote.api.AddressAPI
import com.danbam.data.remote.api.AuthAPI
import com.danbam.data.remote.api.FileAPI
import com.danbam.data.remote.api.SearchAPI
import com.danbam.data.remote.api.CrowdFundingAPI
import com.danbam.data.remote.api.FundingAPI
import com.danbam.data.remote.api.QRCodeAPI
import com.danbam.data.remote.interceptor.IndiStrawInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Log.v("HTTP", message) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpclient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        indiStrawInterceptor: IndiStrawInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(indiStrawInterceptor)
        .build()

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
    fun provideFundingAPI(retrofit: Retrofit): FundingAPI = retrofit.create(FundingAPI::class.java)
}