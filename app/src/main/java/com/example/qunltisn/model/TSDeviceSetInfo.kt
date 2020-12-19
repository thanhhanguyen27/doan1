package com.example.qunltisn.model

import com.google.firebase.firestore.Exclude
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*


class TSDeviceSetInfo: Serializable{
    @SerializedName("Image")
    var Image:String = ""
    @SerializedName("DeviceId")
    var DeviceId:String=""
    @SerializedName("DeviceName")
    var DeviceName:String=""
    @SerializedName("DeviceCode")
    var DeviceCode:String=""
    @SerializedName("DeviceValue")
    var DeviceValue: Int=0
    @SerializedName("QRCode")
    var QRCode: String=""
    @SerializedName("Type")
    var Type:String=""
    @SerializedName("Location")
    var Location:String=""
    @SerializedName("TimeStart")
    var TimeStart:String=""
    @SerializedName("Manufacturer")
    var Manufacturer:String=""
    @SerializedName("TimeEnd")
    var TimeEnd:String=""
    @SerializedName("Note")
    var Note:String=""
    @SerializedName("TimeCreate")
    var TimeCreate:String=""
    @SerializedName("CreaterId")
    var CreaterId:String=""
    @SerializedName("Origin")
    var Origin:String=""
    @SerializedName("OrganizationID")
    var OrganizationID:String=""
    @SerializedName("ReferenceCode")
    var ReferenceCode:String=""
    @SerializedName("Schedule")
    var Schedule:Int=0
    @SerializedName("Status")
    var Status:Int=0


    constructor()
    constructor(Image: String, DeviceId: String, DeviceName: String, DeviceCode: String, DeviceValue: Int, QRCode: String, Type: String, Location:String, TimeStart: String, Manufacturer:String, TimeEnd: String, Note: String, TimeCreate: String, CreaterId: String, Origin:String, OrganizationId: String, ReferenceCode: String, Schedule:Int, Status: Int ){
        this.Image= Image
        this.DeviceId= DeviceId
        this.DeviceName= DeviceName
        this.DeviceCode= DeviceCode
        this.DeviceValue= DeviceValue
        this.QRCode=QRCode
        this.Type= Type
        this.Location=Location
        this.TimeStart= TimeStart
        this.Manufacturer=Manufacturer
        this.TimeEnd= TimeEnd
        this.Note=Note
        this.TimeCreate= TimeCreate
        this.CreaterId= CreaterId
        this.Origin=Origin
        this.OrganizationID=OrganizationId
        this.ReferenceCode=ReferenceCode
        this.Schedule=Schedule
        this.Status=Status
    }


}
//    @SerializedName("DeviceSetParaLst")
//    var deviceSetParaLst: Collection of Object=


