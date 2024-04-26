package com.ecommercwebsite.aioscrm.ui.calls.model


import com.fasterxml.jackson.annotation.JsonProperty

data class CallLog(
    @JsonProperty("created_at")
    var createdAt: String?,
    @JsonProperty("duration")
    var duration: String?,
    @JsonProperty("id")
    var id: String?,
    @JsonProperty("lead_name")
    var leadName: String?
)