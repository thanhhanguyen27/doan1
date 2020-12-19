package com.example.qunltisn.model.response.user

import com.example.qunltisn.model.QLTSUserInfo
import com.google.gson.annotations.SerializedName

class QLTSUserLoginRes {
        @SerializedName("RespCode")
        var respcode:Int=0
        @SerializedName("RespText")
        var resptext:String=""
        @SerializedName("Token")
        var token:String=""
        @SerializedName("UserInfo")
        var userinfo= QLTSUserInfo()
        fun toMapLoginRes(): String{
            return "\nRespCode: $respcode " +
                    "\nRespText: $resptext" +
                    "\nUserInfo: {"+
                    "\n Username: ${userinfo.username}"+
                    "\n Fullname: ${userinfo.fullname}"+
                    "\n Sex: ${userinfo.sex}"+
                    "\n Address: ${userinfo.address}"+
                    "\n EmployeeCode: ${userinfo.employeecode}"+
                    "\n Email: ${userinfo.email}"+
                    "\n PhoneNumber: ${userinfo.phonenumber}"+
                    "\n EmployeeType: ${userinfo.employeetype}" +
                    "\n MoreInfo: ${userinfo.moreinfo}" +
                    "\n}"+
                    "\nToken: $token"

        }



}