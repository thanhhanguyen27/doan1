package com.example.qunltisn.model
import com.google.gson.annotations.SerializedName

class UserRegister {
    @SerializedName ("Fullname")
    var fullname: String=""
    @SerializedName("Address")
    var address:String=""
    @SerializedName("Email")
    var email:String=""
    @SerializedName("PhoneNumber")
    var phonenumber:String=""
    @SerializedName("Sex")
    var sex:String= " "
}