package com.example.qunltisn.model.response.user

import com.google.gson.annotations.SerializedName

class QLTSCheckOTPLostPasswordRes {
     @SerializedName("RespCode")
    var respcode:Int=0
    @SerializedName("RespText")
    var resptext:String=""
    @SerializedName("Notify")
    var notify:String=""
    fun toMapQLTSCheckOTPLostPasswordRes():String{
        return "\nRespCode: $respcode " +
                "\nRespText: $resptext" +
                "\nNotify: $notify"
    }
}