package com.example.qunltisn.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class QLTSUserInfo: Serializable {
        @SerializedName("UserName")
        var username: String = ""

        @SerializedName("FullName")
        var fullname: String = ""

        @SerializedName("Address")
        var address: String = ""

        @SerializedName("Email")
        var email: String = ""

        @SerializedName("PhoneNumber")
        var phonenumber: String = ""

        @SerializedName("Sex")
        var sex: String = ""

        @SerializedName("EmployeeCode")
        var employeecode: String = ""

        @SerializedName("EmployeeType")
        var employeetype: String = ""

        @SerializedName("MoreInfo")
        var moreinfo: String = ""

        constructor()
        constructor(username: String, fullname:String, address: String, email:String, phonenumber: String, sex: String, employeecode: String, employeetype: String, moreinfo:String){
                this.username= username
                this.fullname= fullname
                this.address= address
                this.email= email
                this.phonenumber = phonenumber
                this.employeetype= employeetype
                this.employeecode= employeecode
                this.moreinfo= moreinfo
        }
}



