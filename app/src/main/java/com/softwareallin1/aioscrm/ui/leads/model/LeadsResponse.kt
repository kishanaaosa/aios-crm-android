package com.softwareallin1.aioscrm.ui.leads.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class LeadsResponse(
    @JsonProperty("lead_list")
    var leads: ArrayList<LeadsList>?
)