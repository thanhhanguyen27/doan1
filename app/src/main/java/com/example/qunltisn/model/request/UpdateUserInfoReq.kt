package com.example.qunltisn.model.request

import com.example.qunltisn.model.QLTSUserInfo
import com.google.gson.annotations.SerializedName

class UpdateUserInfoReq (
    @SerializedName("UserName")
    var username: String ="",
    @SerializedName("Token")
    var token: String= "",
    @SerializedName("UserInfo")
    var userinfo : QLTSUserInfo

)