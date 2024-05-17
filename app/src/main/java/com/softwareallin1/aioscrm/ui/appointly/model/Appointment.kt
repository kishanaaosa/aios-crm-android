package com.softwareallin1.aioscrm.ui.appointly.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


data class Appointment(
    @JsonProperty("id")
    var id: String?,
    @JsonProperty("subject")
    var subject: String?,
    @JsonProperty("description")
    var description: String?,
    @JsonProperty("source")
    var source: String?,
    @JsonProperty("status")
    var status: String?,
    @JsonProperty("date")
    var date: String?,
    @JsonProperty("time")
    var time: String?,
    @JsonProperty("initiatedBy")
    var initiatedBy: String?,
)