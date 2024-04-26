package com.ecommercwebsite.aioscrm.ui.leads.model


import com.fasterxml.jackson.annotation.JsonProperty

data class LeadsResponse(
    @JsonProperty("lead_list")
    var leads: ArrayList<LeadsList>?
)