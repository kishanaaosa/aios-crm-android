package com.ecommercwebsite.aioscrm.ui.login.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class LoginRequest : Parcelable {
    @IgnoredOnParcel
    @JsonProperty("user")
    var email: String? = ""

    @JsonProperty("user")
    var password: String? = ""
}