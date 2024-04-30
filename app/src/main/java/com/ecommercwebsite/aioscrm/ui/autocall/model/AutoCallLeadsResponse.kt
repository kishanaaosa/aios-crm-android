package com.ecommercwebsite.aioscrm.ui.autocall.model


import com.fasterxml.jackson.annotation.JsonProperty

data class AutoCallLeadsResponse(
    @JsonProperty("lead_list")
    var leads: ArrayList<LeadsList>?
)