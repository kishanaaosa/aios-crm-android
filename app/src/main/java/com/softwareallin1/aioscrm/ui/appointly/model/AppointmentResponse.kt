package com.softwareallin1.aioscrm.ui.appointly.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.softwareallin1.aioscrm.ui.leads.model.LeadsList

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class AppointmentResponse(
    @JsonProperty("appointment_list")
    var appointments: ArrayList<Appointment>? = arrayListOf()
)