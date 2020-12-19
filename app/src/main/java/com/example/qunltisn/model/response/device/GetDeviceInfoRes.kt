package com.example.qunltisn.model.response.device

import com.example.qunltisn.model.QLTSUserInfo
import com.example.qunltisn.model.TSDeviceSetInfo
import com.google.gson.annotations.SerializedName

class GetDeviceInfoRes {
    @SerializedName("RespCode")
    var respcode:Int=0
    @SerializedName("RespText")
    var resptext:String=""
    @SerializedName("DeviceInfo")
    var deviceinfo = TSDeviceSetInfo()
}