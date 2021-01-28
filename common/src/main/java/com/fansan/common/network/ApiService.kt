package com.fansan.common.network

import com.fansan.common.network.request.CertifyRequest
import com.fansan.common.network.request.PlayUserListRequest
import com.fansan.common.pojo.*
import retrofit2.http.*


/**
 *@author  fansan
 *@version 2020/10/20
 */

interface ApiService {

    @POST("Quokka/play/GetPlayMenuList")
    suspend fun getPlayMenuList(): JKResponse<PlayMenuBean>

    @POST("Quokka/play/GetPlayUserList")
    suspend fun getPlayUserList(@Body request: PlayUserListRequest): JKResponse<MutableList<PlayUserListBean>>

    @POST("Quokka/play/GetPlayMenuAllList")
    suspend fun getAllGame(): JKResponse<AllGameBean>

    @POST("Quokka/play/GetUserPlayMenuList")
    suspend fun getTakeOrderGame(): JKResponse<AllGameBean>

    @FormUrlEncoded
    @POST("Quokka/play/GetPlayDanList")
    suspend fun getPlayDanList(@Field("play_id") id: Int?): JKResponse<MutableList<DanBean>>

    @FormUrlEncoded
    @POST("Quokka/play/GetUpdatePlayData")
    suspend fun GetUpdatePlayData(@Field("play_id") id: Int?): JKResponse<GetUpdatePlayDataBean>

    @POST("TAO/Gateway/GetQiNiuToken")
    suspend fun getQiNiuToken(): JKResponse<QiNiuEntity>

    @POST("Quokka/play/AddPlayCertify")
    suspend fun addPlayCertify(@Body request: CertifyRequest): NoDataResponse

    @POST("Quokka/play/GetPlayOrderPriceList")
    @FormUrlEncoded
    suspend fun getPriceList(@Field("play_type")type:Int): JKResponse<MutableList<SetPriceBean>>

    @POST("Quokka/play/UpdatePlayCertify")
    @FormUrlEncoded
    suspend fun updatePlayCertify(@FieldMap map: MutableMap<String, Any>): NoDataResponse

    @GET("TAO/Order/PlaceAnOrder")
    suspend fun placeAnOrder(
        @Query("receiveUserId") userId: String, @Query("playId") playId: Int
    ): JKResponse<ConfirmOrderInfoBean>

    @POST("/zbj/live/getMoney")
    suspend fun getMoney(): JKResponse<MoneyBean>

    @POST("TAO/Order/SubmitOrder")
    @FormUrlEncoded
    suspend fun submitOrder(@FieldMap map: MutableMap<String, Any>): JKResponse<SubmitBean>

    @POST("/zbj/Gateway/luckOrder")
    @FormUrlEncoded
    suspend fun luckOrder(@FieldMap map: MutableMap<String, Any>): JKResponse<OrderLuckBean>

    @POST("TAO/Order/OrderInfo")
    @FormUrlEncoded
    suspend fun getOrderInfo(@Field("orderId") id: String): JKResponse<OrderInfoBean>

    @POST("TAO/Order/RemindOrder")
    @FormUrlEncoded
    suspend fun remindOrder(@Field("orderId") id: String): NoDataResponse

    @POST("TAO/Order/GameInfoList")
    @FormUrlEncoded
    suspend fun getGameList(@Field("receiveUserId") id: String): JKResponse<MutableList<GameListBean>>

    @POST("TAO/Order/OrderRecord")
    @FormUrlEncoded
    suspend fun orderRecord(
        @Field("type") type: Int,
        @Field("currentPage") page: Int = 0,
        @Field("pageSize") size: Int = 200
    ): JKResponse<OrderListBean>

    @POST("TAO/Gateway/GetPlayAuthentication")
    suspend fun getAuthentication(): JKResponse<AuthenticationBean>

    @POST("TAO/Order/ReqComplete")
    @FormUrlEncoded
    suspend fun reqComplete(@Field("orderId") id: String): NoDataResponse

    @POST("TAO/Order/RecComplete")
    @FormUrlEncoded
    suspend fun recComplete(@Field("orderId") id: String): NoDataResponse

    @POST("TAO/Order/PlayCancelOrder")
    @FormUrlEncoded
    suspend fun cancelOrder(
        @Field("cancelCause") cause: Int,
        @Field("cancelType") type: Int,
        @Field("orderId") orderId: String
    ): NoDataResponse

    @POST("TAO/Order/ReceiveOrder")
    @FormUrlEncoded
    suspend fun receiveOrder(@Field("orderId") orderId: String): NoDataResponse

    @POST("TAO/Order/RequestRefund")
    @FormUrlEncoded
    suspend fun requestRefund(@Field("orderId") oid: String): NoDataResponse

    @POST("TAO/Order/SubmitRefund")
    @FormUrlEncoded
    suspend fun submitRefund(
        @Field("orderId") orderId: String,
        @Field("refundCause") cause: Int,
        @Field("refundDescription") des: String
    ): NoDataResponse

    @POST("TAO/Order/ProcessRefund")
    @FormUrlEncoded
    suspend fun processRefund(
        @Field("orderId") oid: String,
        @Field("processType") type: Int,
        @Field("refuseRefundCause") cause: Int,
        @Field("refuseRefundDescription") des: String
    ): NoDataResponse

    @POST("TAO/Order/SubmitAppeal")
    @FormUrlEncoded
    suspend fun submitAppeal(@Field("appealType")type:Int,@Field("appealDescription")appealDescription:String,@Field("appealImages")imgs:String,@Field("orderId")orderId:String):NoDataResponse

    @POST("TAO/Order/AppealInfo")
    @FormUrlEncoded
    suspend fun appealInfo(@Field("orderId")oid:String):JKResponse<OrderInfoBean>

    @POST("Quokka/play/GetPlayOrderPriceAllList")
    @FormUrlEncoded
    suspend fun getPlayOrderPriceAllList(@Field("play_type")type:Int):JKResponse<MutableList<PriceBean>>

}