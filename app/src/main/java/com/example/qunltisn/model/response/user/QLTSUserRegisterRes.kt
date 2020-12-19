package com.example.qunltisn.model.response.user

import com.google.gson.annotations.SerializedName

class QLTSUserRegisterRes {
    @SerializedName("RespCode")
    var respcode: Int=0
    @SerializedName("RespText")
    var resptext:String=""
    @SerializedName("Token")
    var token:String=" "

    fun toMapRegisterRes():String{
        return "\nRespCode: $respcode " +
                "\nRespText: $resptext" +
                "\nToken: $token"
    }
}