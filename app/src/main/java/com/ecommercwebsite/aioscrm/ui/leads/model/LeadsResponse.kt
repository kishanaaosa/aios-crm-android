package com.ecommercwebsite.aioscrm.ui.leads.model


import com.fasterxml.jackson.annotation.JsonProperty

data class LeadsResponse(
    @JsonProperty("leads")
    var leads: ArrayList<Data>?
)