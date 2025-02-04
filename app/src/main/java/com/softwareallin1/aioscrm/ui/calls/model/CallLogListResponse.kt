package com.softwareallin1.aioscrm.ui.calls.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class CallLogListResponse(
    @JsonProperty("call_log_list")
    var callLogList: ArrayList<CallLog>?
)