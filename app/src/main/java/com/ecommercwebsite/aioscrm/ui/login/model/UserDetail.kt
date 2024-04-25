package com.ecommercwebsite.aioscrm.ui.login.model


import com.fasterxml.jackson.annotation.JsonProperty

data class UserDetail(
    @JsonProperty("active")
    var active: String?,
    @JsonProperty("admin")
    var admin: String?,
    @JsonProperty("birthday")
    var birthday: String?,
    @JsonProperty("birthplace")
    var birthplace: String?,
    @JsonProperty("days_for_identity")
    var daysForIdentity: String?,
    @JsonProperty("default_language")
    var defaultLanguage: String?,
    @JsonProperty("direction")
    var direction: String?,
    @JsonProperty("email")
    var email: String?,
    @JsonProperty("email_signature")
    var emailSignature: String?,
    @JsonProperty("facebook")
    var facebook: String?,
    @JsonProperty("firstname")
    var firstname: String?,
    @JsonProperty("hourly_rate")
    var hourlyRate: String?,
    @JsonProperty("identification")
    var identification: String?,
    @JsonProperty("is_not_staff")
    var isNotStaff: Int?,
    @JsonProperty("last_ip")
    var lastIp: String?,
    @JsonProperty("last_password_change")
    var lastPasswordChange: String?,
    @JsonProperty("lastname")
    var lastname: String?,
    @JsonProperty("linkedin")
    var linkedin: String?,
    @JsonProperty("marital_status")
    var maritalStatus: String?,
    @JsonProperty("media_path_slug")
    var mediaPathSlug: String?,
    @JsonProperty("nation")
    var nation: String?,
    @JsonProperty("new_pass_key")
    var newPassKey: String?,
    @JsonProperty("new_pass_key_requested")
    var newPassKeyRequested: String?,
    @JsonProperty("password")
    var password: String?,
    @JsonProperty("phonenumber")
    var phonenumber: String?,
    @JsonProperty("profile_image")
    var profileImage: String?,
    @JsonProperty("religion")
    var religion: String?,
    @JsonProperty("role")
    var role: String?,
    @JsonProperty("sex")
    var sex: String?,
    @JsonProperty("skype")
    var skype: String?,
    @JsonProperty("staffid")
    var staffid: String?,
    @JsonProperty("two_factor_auth_code")
    var twoFactorAuthCode: String?,
    @JsonProperty("two_factor_auth_code_requested")
    var twoFactorAuthCodeRequested: String?,
    @JsonProperty("two_factor_auth_enabled")
    var twoFactorAuthEnabled: Int?
)