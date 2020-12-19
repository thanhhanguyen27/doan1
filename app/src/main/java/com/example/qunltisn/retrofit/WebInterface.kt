package com.example.qunltisn.retrofit

import com.example.qunltisn.model.QLTSUserInfo
import com.example.qunltisn.model.TSDeviceSetInfo
import com.example.qunltisn.model.request.CreateMaintenanceScheduleReq
import com.example.qunltisn.model.request.UpdateMaintenanceScheduleReq
import com.example.qunltisn.model.request.UpdateUserInfoReq
import com.example.qunltisn.model.response.device.*
import com.example.qunltisn.model.response.user.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WebInterface {
    @FormUrlEncoded
    @POST ("User/UserLogin")
    fun getQLTSUserLogin(
        @Field("UserName") username:String,
        @Field("Password") password: String
    ): Call<QLTSUserLoginRes>

    @FormUrlEncoded
    @POST ("User/UserRegister")
    fun getQLTSUserRegister(
        @Field("Fullname") fullname:String,
        @Field("Email") email:String,
        @Field("Phone") phone:String,
        @Field("Address") address:String,
        @Field("Sex") sex: String
    ):Call<QLTSUserRegisterRes>

    @FormUrlEncoded
    @POST ("User/LotPassword")
    fun getQLTSLotPassword( @Field("Email") email:String):Call<QLTSLotPasswordRes>

    @FormUrlEncoded
    @POST ("User/CheckOTPLostPassword")
    fun getQLTSCheckOTPLostPassword(
        @Field("Email") email:String,
        @Field("OTPCode") code:String
    ):Call<QLTSCheckOTPLostPasswordRes>

    @FormUrlEncoded
    @POST("User/GetUserInfo")
    fun getQLTSUserInfo(
        @Field("UserName") username: String,
        @Field("Token") token:String
        ):Call<QLTSUserInfoRes>

    @FormUrlEncoded
    @POST("Device/CreateDeviceInfo")
    fun createDeviceInfo(
        @Field("Username") username: String,
        @Field("Token") token:String,
        @Field("DeviceInfo") deviceinfo: TSDeviceSetInfo
    ):Call<CreateDeviceInfoRes>

//    @FormUrlEncoded
//    @POST("Device/UpdateDeviceInfo")
//    fun updateDeviceInfo(
//        @Body updateUserInfoReq: UpdateUserInfoReq
//    ): Call<UpdateDeviceInfoRes>

    @FormUrlEncoded
    @POST("Device/RemoveDeviceInfo")
    fun RemoveDeviceInfo(
        @Field("Username") username: String,
        @Field("Token") token:String,
        @Field("DeviceId") deviceid: Int
    ):Call<RemoveDeviceInfoRes>

    @FormUrlEncoded
    @POST("Device/GetDeviceByUser")
    fun GetDeviceByUser(
        @Field("Username") username: String,
        @Field("Token") token:String
    ): Call<GetDeviceByUserRes>

    @POST("User/UpdateUserInfo")
    fun GetUpdateUserInfo(
        @Body updateUserInfoReq: UpdateUserInfoReq
    ):Call<UpdateUserInfoRes>

    @FormUrlEncoded
    @POST("User/ChangePassword")
    fun ChangePassword(
        @Field("UserName") username: String,
        @Field("Token") token: String,
        @Field("Password") password: String,
        @Field("PasswordNew") passwordnew: String
    ): Call<ChangePasswordRes>

    @POST("TSMaintenance/CreateMaintenanceSchedule")
    fun CreateMaintenanceSchedule(
       @Body CreateMaintenanceScheduleReq: CreateMaintenanceScheduleReq
    ):Call<CreateDeviceInfoRes>

    @FormUrlEncoded
    @POST("TSMaintenance/GetMaintenanceScheduleByID")
    fun GetMaintenanceScheduleByID(
        @Field("UserName") username: String,
        @Field("Token") token: String,
        @Field("DocumentID") DocumentID:Int
    ):Call<GetMaintenanceScheduleByIDRes>

    @FormUrlEncoded
    @POST("TSMaintenance/UpdateMaintenanceSchedule")
    fun UpdateMaintenanceSchedule(
        @Body UpdateMaintenanceScheduleReq:UpdateMaintenanceScheduleReq
    ):Call<UpdateMaintenanceScheduleRes>

    @FormUrlEncoded
    @POST("TSMaintenance/DelMaintenanceSchedule")
    fun DelMaintenanceSchedule(
        @Field("UserName") username: String,
        @Field("Token") token: String,
        @Field("DocumentID") DocumentID:Int
    ):Call<DelMaintenanceScheduleRes>

    @FormUrlEncoded
    @POST("TSMaintenance/GetMaintenanceScheduleByOrg")
    fun GetMaintenanceScheduleByOrg(
        @Field("UserName") username: String,
        @Field("Token") token: String,
        @Field("DocumentID") DocumentID:Int,
        @Field("NumberRow") NumberRow:Int,
        @Field("PageNumber") PageNumber:Int
    )
}