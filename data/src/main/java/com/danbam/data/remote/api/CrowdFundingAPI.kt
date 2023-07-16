package com.danbam.data.remote.api

import com.danbam.data.remote.request.FundingCreateRequest
import com.danbam.data.remote.response.FundingPageResponse
import com.danbam.data.remote.response.FundingDetailResponse
import com.danbam.data.remote.response.FundingResponse
import com.danbam.data.remote.response.MyFundingResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CrowdFundingAPI {
    @POST("${EndPoint.CROWD_FUNDING}")
    suspend fun fundingCreate(
        @Body fundingCreateRequest: FundingCreateRequest
    ): Response<Void?>

    @GET("${EndPoint.CROWD_FUNDING}/popular/list")
    suspend fun fundingPopularList(): List<FundingResponse>

    @GET("${EndPoint.CROWD_FUNDING}/{idx}")
    suspend fun fundingDetail(
        @Path("idx") fundingIndex: Long
    ): FundingDetailResponse

    @GET("${EndPoint.CROWD_FUNDING}/list")
    suspend fun fundingAll(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): FundingPageResponse

    @GET("${EndPoint.CROWD_FUNDING}/my")
    suspend fun fundingMy(): List<FundingResponse>

    @GET("${EndPoint.CROWD_FUNDING}/my/{crowdfundingIdx}")
    suspend fun fundingMyDetail(
        @Path("crowdfundingIdx") crowdfundingIdx: Long
    ): MyFundingResponse
}