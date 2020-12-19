package com.example.qunltisn.model.response.device

import com.example.qunltisn.model.TSMaintenanceScheduleInfo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GetMaintenanceScheduleByIDRes:Serializable {
    @SerializedName("RespCode")
    var respcode: Int = 0

    @SerializedName("RespText")
    var resptext: String = ""

    @SerializedName("MaintenanceScheduleInfo")
    var MaintenanceScheduleInfo: TSMaintenanceScheduleInfo= TSMaintenanceScheduleInfo()
}