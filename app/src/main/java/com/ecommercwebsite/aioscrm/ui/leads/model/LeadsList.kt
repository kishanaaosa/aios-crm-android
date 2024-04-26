package com.ecommercwebsite.aioscrm.ui.leads.model


import com.fasterxml.jackson.annotation.JsonProperty

data class LeadsList(
    @JsonProperty("company")
    var company: String?,
    @JsonProperty("dateadded")
    var dateadded: String?,
    @JsonProperty("email")
    var email: String?,
    @JsonProperty("id")
    var id: String?,
    @JsonProperty("lastcontact")
    var lastcontact: String?,
    @JsonProperty("lead_value")
    var leadValue: String?,
    @JsonProperty("name")
    var name: String?,
    @JsonProperty("phonenumber")
    var phonenumber: String?,
    @JsonProperty("source")
    var source: String?,
    @JsonProperty("status")
    var status: String?
)