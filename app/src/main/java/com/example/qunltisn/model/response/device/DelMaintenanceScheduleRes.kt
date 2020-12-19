package com.example.qunltisn.model.response.device

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DelMaintenanceScheduleRes:Serializable {
    @SerializedName("RespCode")
    var respcode:Int=0
    @SerializedName("RespText")
    var resptext:String=""
}