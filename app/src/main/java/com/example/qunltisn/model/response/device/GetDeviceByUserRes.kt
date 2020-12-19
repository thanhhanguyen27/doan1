package com.example.qunltisn.model.response.device

import com.example.qunltisn.adapter.DeviceAdapter
import com.example.qunltisn.model.TSDeviceSetInfo
import com.google.gson.annotations.SerializedName

class GetDeviceByUserRes {
    @SerializedName("RespCode")
    var respcode:Int=0
    @SerializedName("RespText")
    var resptext:String=""
    @SerializedName("DeviceLst")
    var devicelst: ArrayList<TSDeviceSetInfo> = arrayListOf()
    fun toMApGetDeviceByUser():String{
        return "\nRespCode: $respcode " +
                "\nRespText: $resptext"
    }
}