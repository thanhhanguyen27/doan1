package com.example.qunltisn.model.response.user

import com.google.gson.annotations.SerializedName

class ChangePasswordRes {
    @SerializedName("RespCode")
    var respcode: Int = 0
    @SerializedName("RespText")
    var resptext: String = ""
    fun toMapChangePassword(): String {
        return "\nRespCode: $respcode " +
                "\nRespText: $resptext"
    }
}