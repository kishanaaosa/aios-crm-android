package com.ecommercwebsite.aioscrm.ui.login.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class LoginResponse(
    @JsonProperty("user_detail")
    var userDetail: UserDetail?
)