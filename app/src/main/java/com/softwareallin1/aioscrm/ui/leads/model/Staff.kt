package com.softwareallin1.aioscrm.ui.leads.model


import com.fasterxml.jackson.annotation.JsonProperty

data class Staff(
    @JsonProperty("firstname")
    var firstname: String?,
    @JsonProperty("lastname")
    var lastname: String?,
    @JsonProperty("staffid")
    var staffid: String?
)