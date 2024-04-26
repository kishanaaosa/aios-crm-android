package com.ecommercwebsite.aioscrm.ui.calls.model


import com.fasterxml.jackson.annotation.JsonProperty

data class CallLogListResponse(
    @JsonProperty("call_log_list")
    var callLogList: ArrayList<CallLog>?
)