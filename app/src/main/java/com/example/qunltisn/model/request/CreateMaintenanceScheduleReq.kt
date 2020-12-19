package com.example.qunltisn.model.request

import com.example.qunltisn.model.TSMaintenanceScheduleInfo
import com.google.gson.annotations.SerializedName

class CreateMaintenanceScheduleReq (
    @SerializedName("UserName")
    var username: String ="",
    @SerializedName("Token")
    var token: String= "",
    @SerializedName("MaintenanceScheduleInfo")
    var MaintenanceScheduleInfo:TSMaintenanceScheduleInfo
)