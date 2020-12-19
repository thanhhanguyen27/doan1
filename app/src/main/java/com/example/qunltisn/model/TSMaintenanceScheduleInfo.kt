package com.example.qunltisn.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TSMaintenanceScheduleInfo: Serializable {
    @SerializedName("DocumentID")
    var DocumentID:Int=0
    @SerializedName("OrganizationID")
    var OrganizationID:String=""
    @SerializedName("CreaterID")
    var CreaterID:String=""
    @SerializedName("TimeCreate")
    var TimeCreate:String=""
    @SerializedName("TimeStart")
    var TimeStart:String=""
    @SerializedName("TimeEnd")
    var TimeEnd:String=""
    @SerializedName("Description")
    var Description:String=""
    @SerializedName("Note")
    var Note:String=""
    @SerializedName("Status")
    var Status:Int=0
    @SerializedName("DeviceLst")
    var DeviceLst:ArrayList<TSDeviceSetInfo> = ArrayList()
}