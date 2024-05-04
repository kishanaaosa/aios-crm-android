package com.softwareallin1.aioscrm.ui.home.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class CheckAttendanceResponse(
    @JsonProperty("is_attendance")
    var isAttendance: Boolean? = false
)