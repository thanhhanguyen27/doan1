package com.example.qunltisn.model.response.device

import com.google.gson.annotations.SerializedName

class CreateDeviceInfoRes {
    @SerializedName("RespCode")
    var respcode:Int=0
    @SerializedName("RespText")
    var resptext:String=""
    fun toMapCreateDeviceInfo():String{
        return "\nRespCode: $respcode " +
                 "\nRespText: $resptext"
}
}