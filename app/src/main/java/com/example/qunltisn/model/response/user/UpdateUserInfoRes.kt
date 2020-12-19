package com.example.qunltisn.model.response.user

import com.google.gson.annotations.SerializedName

class UpdateUserInfoRes {
    @SerializedName("RespCode")
    var respcode: Int=0
    @SerializedName("RespText")
    var resptext:String=""

    fun toMApUpdateUserInfoRes():String{
        return "\nRespCode: $respcode " +
                "\nRespText: $resptext"
    }
}